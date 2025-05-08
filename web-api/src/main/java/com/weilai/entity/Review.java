package com.weilai.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
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
@TableName("cc_review")
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @TableField("content")
    private String content;


//    @TableField("reply")
//    private Integer reply;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField("likes")
    private Integer likes;


    @TableField("blog_id")
    private Long blogId;


    @TableField("reply_id")
    private Long replyId;


    @TableField("user_id")
    private Long userId;

    @TableField("review_type")
    private Integer reviewType;
}
