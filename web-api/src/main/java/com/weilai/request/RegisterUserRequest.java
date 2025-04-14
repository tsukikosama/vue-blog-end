package com.weilai.request;

import com.weilai.entity.User;
import lombok.Data;

@Data
public class RegisterUserRequest extends User {
    private Integer code;
}
