package com.weilai.api.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginReq {
    private String username;
    private String password;
}
