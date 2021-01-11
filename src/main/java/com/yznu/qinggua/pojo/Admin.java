package com.yznu.qinggua.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Admin {

    private int id;

    private String name;

    private String pwd;
    /**
     * 账号状态
     * */
    private int flag;

}
