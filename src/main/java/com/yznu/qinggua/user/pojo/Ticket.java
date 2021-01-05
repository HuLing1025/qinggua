package com.yznu.qinggua.user.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class Ticket {

    private int id;

    /**
     * 订购人
     * */
    private int uid;

    /**
     * 场次(影片安排表ID)
     * */
    private int rid;

    /**
     * 座位
     * */
    private int sid;

    /**
     * 支付状态
     * */
    private int flag;

    /**
     * 编号
     * */
    private int number;

    /**
     * 购票时间
     * */
    private Date createTime;
}
