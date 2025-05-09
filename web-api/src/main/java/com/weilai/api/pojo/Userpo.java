package com.weilai.api.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Userpo {
    private Integer uid;
    private String username;
    private String userIcon;
    private String about;
    private String email;
    private String createTime;
    private String isValid;
    private Integer userType;
    private String nickname;
    private String ban;
}
