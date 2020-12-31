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
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 所属影片
     */
    private Integer fid;

    /**
     * 放映时间
     */
    private LocalDateTime starttime;

    /**
     * 语言/制式
     */
    private String standard;

    /**
     * 放映厅
     */
    private Integer rid;

    /**
     * 票价
     */
    private Float price;


}
