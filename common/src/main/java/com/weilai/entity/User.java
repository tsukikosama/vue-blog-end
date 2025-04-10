package com.weilai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(value = "uid",type = IdType.AUTO)
    private Integer uid;
    private String username;

    private String userIcon;
    private String password;
    private String createTime;
    private Integer userType;
    private String isValid;
    private Integer version;
    private String email;
    private String about;
    private String nickname;
    private String ban;
}
