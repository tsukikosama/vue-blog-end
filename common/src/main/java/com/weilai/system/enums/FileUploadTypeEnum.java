package com.weilai.system.enums;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FileUploadTypeEnum {
    LOCAL_STORAGE(1,"本地存储"),
    ALI_YUN(2,"阿里云"),
    TX_YUN(3,"腾讯云");
    private final Integer code;
    private final String value;
}
