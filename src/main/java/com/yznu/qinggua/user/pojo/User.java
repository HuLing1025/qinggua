package com.yznu.qinggua.user.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class User {

    private  int id;

    private String name;

    private String pwd;

    private String tel;

    private String email;

    private String qq;

    /**
     * 账户余额
     * */
    private float balance;

    /**
     * 注册时间
     * */
    private Date regtime;
}
