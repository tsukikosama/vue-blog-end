package com.miku.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
public class Type {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String typeName;
}
