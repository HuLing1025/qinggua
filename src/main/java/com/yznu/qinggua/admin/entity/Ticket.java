package com.yznu.qinggua.admin.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author huling
 * @since 2020-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订购人
     */
    private Integer uid;

    /**
     * 场次(影片安排表ID)
     */
    private Integer rid;

    /**
     * 座位
     */
    private Integer sid;

    /**
     * 支付状态
     */
    private Integer flag;

    /**
     * 序列号
     */
    private Integer number;

    /**
     * 订购时间
     */
    private LocalDateTime createtime;


}
