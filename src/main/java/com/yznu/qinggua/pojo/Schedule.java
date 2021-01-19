package com.yznu.qinggua.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class Schedule {
    private int id;

    /**
     * 放映电影ID
     * */
    private int fid;

    /**
     * 放映时间
     * */
    private Date startTime;

    /**
     * 语言/制式 例如: 国语/3D
     * */
    private String standard;

    /**
     * 放映厅
     * */
    private int rid;

   /**
    * 票价
    * */
    private float price;
}
