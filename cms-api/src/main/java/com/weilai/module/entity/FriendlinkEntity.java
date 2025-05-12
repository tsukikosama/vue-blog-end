package com.weilai.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
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
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("cc_friendlink")
@ApiModel(value = "FriendlinkEntity对象", description = "")
public class FriendlinkEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("网站链接")
    @TableField("web_url")
    private String webUrl;

    @ApiModelProperty("网站名字")
    @TableField("web_name")
    private String webName;

    @ApiModelProperty("网站描述")
    @TableField("web_descript")
    private String webDescript;

    @ApiModelProperty("添加时间")
    @TableField("web_time")
    private LocalDate webTime;

    @ApiModelProperty("网站图片")
    @TableField("web_img")
    private String webImg;

    @ApiModelProperty("0为为审核通过 1为审核通过")
    @TableField("web_access")
    private Integer webAccess;

    @ApiModelProperty("用户邮箱")
    @TableField("web_email")
    private String webEmail;
}
