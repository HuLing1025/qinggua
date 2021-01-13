package com.yznu.qinggua;

import com.yznu.qinggua.cron.SpiderSchedule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.yznu.qinggua.*.mapper")
@SpringBootApplication
@EnableScheduling
public class QingguaApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(QingguaApplication.class, args);
        // 启动程序时爬取一次数据
        new SpiderSchedule().spiderWork();
    }

}
