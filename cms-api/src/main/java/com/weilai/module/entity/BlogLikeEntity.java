package com.weilai.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author miku
 * @since 2025-04-27
 */
@Getter
@Setter
@TableName("cc_blog_like")
@ApiModel(value = "BlogLikeEntity对象", description = "")
public class BlogLikeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("组件")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("点赞博客id")
    @TableField("blog_id")
    private Long blogId;

    @ApiModelProperty("点赞时间")
    @TableField("create_time")
    private LocalDateTime createTime;
}
