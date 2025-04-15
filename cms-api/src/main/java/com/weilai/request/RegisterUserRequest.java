package com.weilai.request;

import com.weilai.entity.User;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterUserRequest extends User {
    @NotNull(message = "验证码不能为空")
    private Integer code;
}
