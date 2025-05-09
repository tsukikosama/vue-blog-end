package com.weilai.module.request;


import com.weilai.module.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RegisterUserRequest extends User {
    @NotNull(message = "验证码不能为空")
    private Integer code;
}
