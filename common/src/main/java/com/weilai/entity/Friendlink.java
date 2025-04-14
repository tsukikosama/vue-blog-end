package com.weilai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

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
@TableName("cc_friendlink")

public class Friendlink implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("web_url")
    private String webUrl;

    @TableField("web_name")
    private String webName;

    @TableField("web_descript")
    private String webDescript;

    @TableField("web_time")
    private LocalDate webTime;

    @TableField("web_img")
    private String webImg;

    @TableField("web_access")
    private Integer webAccess;
    @TableField("web_email")
    private String webEmail;
}
