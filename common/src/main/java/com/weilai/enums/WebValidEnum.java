package com.weilai.enums;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WebValidEnum {
    FAILURE_URL(0,"失效"),
    VALID_URL(1,"生效");
    private final Integer code;
    private final String value;
}
