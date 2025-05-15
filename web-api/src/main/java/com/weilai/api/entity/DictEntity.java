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
@TableName("cc_dict")
@ApiModel(value = "DictEntity对象", description = "")
public class DictEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("组件")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("类型id")
    @TableField("code_id")
    private Long codeId;

    @ApiModelProperty("字典值")
    @TableField("code_value")
    private String codeValue;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("更新用户")
    @TableField("create_user")
    private Long createUser;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;
}
