package com.weilai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Module {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String moduleName;
    private Integer moduleType;
    private String date;
}
