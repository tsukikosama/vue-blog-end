package com.weilai.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2025-06-13
 */
@Getter
@Setter
@TableName("sys_dict")
public class SysDictEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 组件
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 类型id
     */
    @TableField("name")
    private Long name;

    /**
     * 字典值
     */
    @TableField("code")
    private String code;

    /**
     * 创建时间
     */
    @TableField("description")
    private String description;
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
     * 更新用户
     */
    @TableField("create_user")
    private Long createUser;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
}
