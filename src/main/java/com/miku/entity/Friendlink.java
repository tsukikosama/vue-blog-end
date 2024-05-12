package com.miku.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  Friendlink {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String webUrl;
    private String webDescript;
    private String webName;
    private String webTime;
    private String webImg;
    private String webAccess;
    private String webEmail;
}
