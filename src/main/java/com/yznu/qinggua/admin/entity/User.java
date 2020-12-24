package com.yznu.qinggua.admin.entity;

import java.io.Serializable;
import java.util.Date;

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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     * */
    @Id
    private int id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 联系方式
     */
    private String tel;

    /**
     * E-mail
     */
    private String email;

    /**
     * QQ
     */
    private String qq;

    /**
     * 账户金额
     */
    private float count;

    /**
     * 注册时间
     */
    private Date regtime;


}
