package com.yznu.qinggua.admin.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author huling
 * @since 2020-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 影片编号
     */
    private Integer fid;

    /**
     * 评论人(null时为匿名评论)
     */
    private Integer uid;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private Date createtime;


}
