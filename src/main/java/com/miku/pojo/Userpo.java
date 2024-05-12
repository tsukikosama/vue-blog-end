package com.miku.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Userpo {
    private Integer uid;
    private String username;
//    @TableField(value = "user_icon")
    private String userIcon;
//    private String token;
    private String about;
    private String email;
    private String createTime;
    private String isValid;
    private Integer userType;
    private String nickname;
    private String ban;
}
