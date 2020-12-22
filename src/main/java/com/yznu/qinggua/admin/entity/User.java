package com.yznu.qinggua.admin.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

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
    @TableField("QQ")
    private String qq;

    /**
     * 账户金额
     */
    private float count;

    /**
     * 注册时间
     */
    private LocalDateTime regtime;


}
