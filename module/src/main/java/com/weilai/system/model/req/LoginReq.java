package com.weilai.system.model.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Schema(description = "创建或修改商品参数")
public class LoginReq {
    @Schema(description = "用户名")
    @NotNull(message = "用户名不能为空")
    private String username;
    @Schema(description = "密码")
    @NotNull(message = "密码不能为空")
    private String password;
    @Schema(description = "验证码")
    private String code;
}
