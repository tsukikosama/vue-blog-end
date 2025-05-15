package com.weilai.api.entity;

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
 * @since 2025-05-15
 */
@Getter
@Setter
@TableName("cc_life_note")
@ApiModel(value = "LifeNoteEntity对象", description = "")
public class LifeNoteEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("原图地址")
    @TableField("origin_url")
    private String originUrl;

    @ApiModelProperty("网页压缩图地址")
    @TableField("web_url")
    private String webUrl;

    @ApiModelProperty("发布者id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;
}
