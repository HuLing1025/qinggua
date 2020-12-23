package com.yznu.qinggua.admin.entity;

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
public class Seat implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     * */
    @Id
    private int id;

    /**
     * 所属播放厅
     */
    private Integer rid;

    /**
     * 排
     */
    private Integer row;

    /**
     * 号
     */
    private Integer number;


}
