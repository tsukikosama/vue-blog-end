package com.weilai.system.model.resp;

import com.weilai.system.common.CommonFiled;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "用户登录请求参数")
public class UserInfoResp extends CommonFiled {
    private Long id;
    private String username;
    private String nickname;
    private String email;
    private String phone;
    private String avatar;
    private String description;
    private String deptId;
}
