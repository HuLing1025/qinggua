package com.yznu.qinggua.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Random;

public class HttpClientDownPage {

    public static Document sendGet(String url) {
        Random random = new Random();
        int n = random.nextInt(UserAgents.USERAGENT.size());
        //设置代理，模范浏览器
        String USER_AGENT = UserAgents.USERAGENT.get(n);
        //1.生成httpclient，相当于该打开一个浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
        CloseableHttpResponse response = null;
        String html = null;
        //2.创建get请求，相当于在浏览器地址栏输入 网址
        HttpGet request = new HttpGet(url);
        try {
            request.setHeader("User-Agent", USER_AGENT);
            request.setConfig(requestConfig);
            //3.执行get请求，相当于在输入地址栏后敲回车键
            try{
                response = httpClient.execute(request);
            }catch (Exception e){
                System.out.println("失败.: " + USER_AGENT + "\nURL: " + url);
                return null;
            }
            if(response == null){
                System.out.println("失败..: " + USER_AGENT + "\nURL: " + url);
                return null;
            }
            //4.判断响应状态为200，进行处理
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //5.获取响应内容
                HttpEntity httpEntity = response.getEntity();
                html = EntityUtils.toString(httpEntity, "GBK");
            } else {
                System.out.println("失败...: " + USER_AGENT + "\nURL: " + url);
                //如果返回状态不是200，比如404（页面不存在）等，根据情况做处理，这里略
                System.out.println("返回状态不是200");
                System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
            }
        } catch (IOException e) {
            System.out.println("失败....: " + USER_AGENT + "\nURL: " + url);
            e.printStackTrace();
        } finally {
            //6.关闭
            HttpClientUtils.closeQuietly(response);
            HttpClientUtils.closeQuietly(httpClient);
        }
        if(html == null) {
            System.out.println("失败.....: " + USER_AGENT + "\nURL: " + url);
            return null;
        }
        Document doc = Jsoup.parse(html);
        return doc;
    }
}