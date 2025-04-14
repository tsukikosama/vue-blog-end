package com.weilai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author miku
 * @since 2025-04-14
 */
@Getter
@Setter
@TableName("cc_user")

public class User implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @TableField("username")
    private String username;


    @TableField("avatar")
    private String avatar;


    @TableField("password")
    private String password;


    @TableField("create_time")
    private LocalDate createTime;


    @TableField("user_type")
    private Integer userType;

    @TableField("is_valid")
    private Integer isValid;


    @TableField("version")
    private Integer version;


    @TableField("email")
    private String email;


    @TableField("about")
    private String about;


    @TableField("nickname")
    private String nickname;


    @TableField("ban")
    private String ban;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
