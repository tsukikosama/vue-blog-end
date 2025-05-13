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
 * @since 2025-05-13
 */
@Getter
@Setter
@TableName("cc_angry_pig")
@ApiModel(value = "AngryPigEntity对象", description = "")
public class AngryPigEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户")
    @TableField("name")
    private String name;

    @ApiModelProperty("原因")
    @TableField("cause")
    private String cause;

    @ApiModelProperty("时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("创建人")
    @TableField("create_user")
    private Long createUser;

    @ApiModelProperty("类型")
    @TableField("type")
    private Integer type;


}
