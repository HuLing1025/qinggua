package com.yznu.qinggua.pojo;

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

    // new
    private String score;

    private String director;

    private String actors;

    private String country;

    private String type;

    private int duration;

    private Date releaseTime;

    private String image;

    private String details;

    // new
    private int voteCount;

}
