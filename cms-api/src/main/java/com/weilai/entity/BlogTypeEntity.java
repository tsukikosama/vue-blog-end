package com.weilai.entity;

import com.baomidou.mybatisplus.annotation.*;

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
 * @since 2025-05-08
 */
@Getter
@Setter
@TableName("cc_blog_type")
@ApiModel(value = "BlogTypeEntity对象", description = "")
public class BlogTypeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("博客id")
    @TableField("blog_id")
    private Long blogId;

    @ApiModelProperty("帖子id")
    @TableField("tag_id")
    private Long tagId;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
