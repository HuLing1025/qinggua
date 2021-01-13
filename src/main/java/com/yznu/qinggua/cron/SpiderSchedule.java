package com.yznu.qinggua.cron;

import com.yznu.qinggua.pojo.Filminfo;
import com.yznu.qinggua.service.IFilmInfoService;
import com.yznu.qinggua.utils.*;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpiderSchedule {
    @Autowired
    IFilmInfoService iFilmInfoService;

    @Autowired
    DDRobot ddRobot;

    @Scheduled(cron = Global.CROONERS)
    public void spiderWork() throws InterruptedException {
        ddRobot.post(JsonTool.getMessJson("开始爬取: " + Global.BASEURL1));
        // 获取正在热映页面
        Document doc1 = HttpClientDownPage.sendGet(Global.BASEURL1);
        List<Filminfo> films = null;
        if(doc1 == null){
            ddRobot.post(JsonTool.getMessJson("-------主页面为空,可能原因:反爬虫机制------"));
            System.out.println("-------主页面为空,可能原因:反爬虫机制------");
        }else{
            // 页面元素过滤条件
            List<String> filters1 = new ArrayList<>();
            filters1.add("list-item");
            // 获取电影数据
            films = Analyticaldata.analytical(doc1, Global.BASEURL1, filters1);
            // 忽略数(已存在)
            int ignored = 0;
            // 失败数
            int error = 0;
            for (Filminfo film : films) {
                Result result = iFilmInfoService.isFilmExists(film.getTitle());
                if (result.getCode() == 200) {      // 存在时忽略
                    ++ ignored;
                }else if(result.getCode() == 400){  // 不存在时新增
                     // 存库
                    Result insertResult = iFilmInfoService.addFilm(film);
                    if (insertResult.getCode() != 200) {
                        // 发送错误信息
                        ddRobot.post(JsonTool.getMessJson(insertResult.getMessage()));
                        ++ error;
                    }
                }else{                             // 打印错误信息
                    System.out.println(result.getMessage());
                }
            }
            // 发送钉钉群消息
            ddRobot.post(JsonTool.getMessJson("忽略: " + ignored + "\n总数: " + films.size() + "\n失败: " + error));
        }
        // 正在热映和即将上映的电影class都为'list-item',故不需要单独处理即将上映电影
        // 获取即将上映的页面
        /*Document doc2 = HttpClientDownPage.sendGet(Globle.BASEURL2);
        if(doc2 == null){
            System.out.println("------即将上映页面为空,可能原因:反爬虫机制------");
        }else{
            // 页面元素过滤条件
            List<String> filters2 = new ArrayList<>();
            filters2.add("thumb");
            Analyticaldata.analytical(doc2, Globle.BASEURL2,filters2);
        }*/
    }
}
