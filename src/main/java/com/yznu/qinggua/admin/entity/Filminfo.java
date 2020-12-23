package com.yznu.qinggua.admin.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

/**
 * <p>
 * 
 * </p>
 *
 * @author huling
 * @since 2020-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Filminfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     * */
    @Id
    private int id;

    /**
     * 影片名称
     */
    private String title;

    /**
     * 导演
     */
    private String director;

    /**
     * 主演
     */
    private String actors;

    /**
     * 国家/地区
     */
    private String country;

    /**
     * 影片类型
     */
    private String type;

    /**
     * 片长(单位ms)
     */
    private long duration;

    /**
     * 上映时间
     */
    private String releasetime;

    /**
     * 图片
     */
    private String image;

    /**
     * 详细简介
     */
    private String details;


}
