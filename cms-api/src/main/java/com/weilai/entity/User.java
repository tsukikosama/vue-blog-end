package com.weilai.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
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
    @NotNull(message = "用户名不能为空")
    private String username;


    @TableField("avatar")
    private String avatar;

    @NotNull(message = "密码不能为空")
    @TableField("password")
    private String password;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField("user_type")
    private Integer userType;

    @TableField("is_valid")
    private Integer isValid;


    @TableField("version")
    private Integer version;


    @TableField("email")
    @NotNull(message = "邮箱不能为空")
    private String email;


    @TableField("about")
    private String about;

    @TableField("nickname")
    private String nickname;

    @TableField("ban")
    private String ban;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
