package com.miku.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Userpo {
    private String username;
//    @TableField(value = "user_icon")
    private String userIcon;
}
