package com.weilai.blog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
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
@TableName("cc_friendlink")
public class FriendlinkEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 网站链接
     */
    @TableField("web_url")
    private String webUrl;

    /**
     * 网站名字
     */
    @TableField("web_name")
    private String webName;

    /**
     * 网站描述
     */
    @TableField("web_descript")
    private String webDescript;

    /**
     * 添加时间
     */
    @TableField("web_time")
    private LocalDate webTime;

    /**
     * 网站图片
     */
    @TableField("web_img")
    private String webImg;

    /**
     * 0为为审核通过 1为审核通过
     */
    @TableField("web_access")
    private Integer webAccess;

    /**
     * 用户邮箱
     */
    @TableField("web_email")
    private String webEmail;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

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
}
