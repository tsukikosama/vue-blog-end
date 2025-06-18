package com.weilai.system.model.req;

import lombok.Data;

@Data
public class UserReq {

    private Long id;

    private String username;

    private String nickname;

    private String password;

    private String email;

    private String phone;

    private String avatar;

    private String description;

    private Integer status;
}
