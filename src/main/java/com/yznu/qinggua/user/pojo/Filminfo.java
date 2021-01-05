package com.yznu.qinggua.user.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class Filminfo {

    private int id;

    private String title;

    private String director;

    private String actors;

    private String country;

    private String type;

    private long duration;

    private Date releaseTime;

    private String image;

    private String details;

}
