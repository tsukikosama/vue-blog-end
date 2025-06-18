package com.weilai.system.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SysUserStatusMenu {

    BAN(0,"禁用"),
    NORMAL(1,"正常")
    ;

    private final Integer value;
    private final String description;
}
