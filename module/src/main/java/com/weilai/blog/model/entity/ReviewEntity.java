package com.weilai.blog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author miku
 * @since 2025-06-13
 */
@Getter
@Setter
@TableName("cc_review")
public class ReviewEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 评论内容
     */
    @TableField("content")
    private String content;

    /**
     * 点赞数
     */
    @TableField("likes")
    private Integer likes;

    /**
     * 博客id
     */
    @TableField("blog_id")
    private Long blogId;

    /**
     * 回复的是哪个评论的id
     */
    @TableField("reply_id")
    private Long replyId;

    /**
     * 评论用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 评论的类型 0是主评论1是子评论
     */
    @TableField("review_type")
    private Integer reviewType;

    /**
     * 回复的用户id
     */
    @TableField("reply_user_id")
    private Long replyUserId;

    /**
     * 评论时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;
}
