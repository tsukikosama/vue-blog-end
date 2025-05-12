package com.weilai.module.response;

import lombok.Data;

@Data
public class UserRecordResponse {
    private Long id;
    private String username;
    private String nickname;
    private String avatar;
    private String createTime;
    private String email;
    private String about;
}
