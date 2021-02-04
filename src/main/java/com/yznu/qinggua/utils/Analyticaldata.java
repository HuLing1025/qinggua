package com.yznu.qinggua.utils;

import com.yznu.qinggua.pojo.Filminfo;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Analyticaldata {

    @Autowired
    public static DDRobot ddRobot;

    public static List<Filminfo> analytical(Document document, String url, List<String> filters) throws InterruptedException {
        //根据网页标签解析源码
        Elements movies = document.getElementsByClass(filters.get(0));
        // System.out.println(movies);
        // 电影数据集合
        List<Filminfo> films = new ArrayList<>();
        for (Element movie : movies) {
           try {
               // 线程静默时间(10s),避免频繁访问接口,导致IP被限制
               Thread.sleep(5000);
               Filminfo filminfo = new Filminfo();
               // 电影名
               filminfo.setTitle(movie.attr("data-title"));
               // 评分
               filminfo.setScore(movie.attr("data-score"));
               // 发布时间
               //filminfo.setReleaseTime(new Date(movie.attr("data-release").substring(0, 9)));
               // 片长
               String duration = movie.attr("data-duration").replaceAll("[^0-9]+", "");
               filminfo.setDuration(Integer.parseInt(!duration.equals("") ? duration : "0"));
               // 国家地区
               filminfo.setCountry( movie.attr("data-region"));
               // 导演
               filminfo.setDirector(movie.attr("data-director").replace(" ",""));
               // 主演
               filminfo.setActors(movie.attr("data-actors").replace(" ",""));
               // 评价人数
               String voteCount = movie.attr("data-votecount");
               filminfo.setVoteCount(Integer.parseInt(!voteCount.equals("") ? voteCount : "0"));
               // 图片路径
               filminfo.setImage(movie.select("img[src]").attr("src"));
               // 详情页URL
               String linkHref = movie.select("a[href]").get(0).attr("href");
               Document detailDoc = HttpClientDownPage.sendGet(linkHref);
               if(detailDoc == null) {
                   ddRobot.post(JsonTool.getMessJson("-------详情页面为空,可能原因:反爬虫机制------"));
                   System.out.println("-------详情页面为空,可能原因:反爬虫机制------");
               }else{
                   // 类型
                   String[] types = new String[0];
                   types = detailDoc.select("span[property=\"v:genre\"]").eachText().toArray(types);
                   String typeString = "";
                   for (String temp : types){
                       typeString += temp + "/";
                   }
                   filminfo.setType(typeString.replace(" ",""));
                   // 上映时间
                   filminfo.setReleaseTime(new Date(detailDoc.select("span[property=\"v:initialReleaseDate\"]").text().substring(0, 10).replace("-", "/")));
                   // 电影简介
                   filminfo.setDetails(detailDoc.select("span[property=\"v:summary\"]").text());
               }
               // 添加到集合
               System.out.println(filminfo);
               films.add(filminfo);
           }catch (Exception e) {
               // 发送预警消息
               //ddRobot.post(JsonTool.getMessJson(e.getMessage()));
           }
        }
        return films;
    }
}
