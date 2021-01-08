package com.yznu.qinggua.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Comment {

    private int id;

    /**
     * 电影ID
     * */
    private int fid;

    /**
     * 评论人ID
     * */
    private int uid;

    /**
     * 评论内容
     * */
    private String content;

}
