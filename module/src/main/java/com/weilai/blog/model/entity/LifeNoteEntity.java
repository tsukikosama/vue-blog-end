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
@TableName("cc_life_note")
public class LifeNoteEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 原图地址
     */
    @TableField("origin_url")
    private String originUrl;

    /**
     * 网页压缩图地址
     */
    @TableField("web_url")
    private String webUrl;

    /**
     * 发布者id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @TableField("create_user")
    private Long createUser;

    /**
     * 更新人
     */
    @TableField("update_user")
    private Long updateUser;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;
}
