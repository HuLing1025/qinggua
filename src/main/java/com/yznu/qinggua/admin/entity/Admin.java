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
 * @since 2020-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;
    /**
     * 登录名
     */
    private String name;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 标识
     */
    private int flag;


}
