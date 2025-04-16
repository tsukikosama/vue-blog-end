package com.weilai.entity;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName("cc_blog")

public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @TableField("title")
    private String title;

    @TableField("picture")
    private String picture;


    @TableField("content")
    private String content;


    @TableField("is_valid")
    private Integer isValid;


//    @TableField("create_date")
//    private LocalDate createDate;


    @TableField("version")
    private Integer version;


    @TableField("tag_id")
    private String tagId;


    @TableField("visit")
    private Integer visit;


    @TableField("simple_title")
    private String simpleTitle;


    @TableField("user_id")
    private Long userId;


    @TableField("state")
    private Integer state;


    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
