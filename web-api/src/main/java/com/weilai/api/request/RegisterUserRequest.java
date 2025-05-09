package com.weilai.api.request;

import com.weilai.api.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequest extends User {
    private Integer code;
}
