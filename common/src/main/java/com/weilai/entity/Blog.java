package com.weilai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @TableId(value = "bid", type = IdType.AUTO)
    private Integer bid;
    private String title;
    private String picture;
    private String content;
    private int isValid;
    private String createDate;
    private int version;
    private Integer uid;
    /**
     * 逗号隔开
     */
    private String tid;

    private int visit;
    private String simpleTitle;


}
