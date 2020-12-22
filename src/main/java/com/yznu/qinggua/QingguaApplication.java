package com.yznu.qinggua;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.yznu.qinggua.*.mapper")
@SpringBootApplication
public class QingguaApplication {

    public static void main(String[] args) {
        SpringApplication.run(QingguaApplication.class, args);
    }

}
