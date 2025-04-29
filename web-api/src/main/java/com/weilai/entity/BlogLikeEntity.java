package com.weilai.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
