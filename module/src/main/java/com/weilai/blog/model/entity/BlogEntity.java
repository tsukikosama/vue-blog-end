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
@TableName("cc_blog")
public class BlogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 图片
     */
    @TableField("picture")
    private String picture;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 是否有效
     */
    @TableField("is_valid")
    private Integer isValid;

    /**
     * 版本
     */
    @TableField("version")
    private Integer version;

    /**
     * 流量数量
     */
    @TableField("visit")
    private Integer visit;

    /**
     * 简化标题
     */
    @TableField("simple_title")
    private String simpleTitle;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 0保存 1发布
     */
    @TableField("state")
    private Integer state;

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
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    @TableField("update_user")
    private Long updateUser;
}
