package com.weilai.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReviewEnum {
    MAIN_REVIEW(0,"主评论"),
    CHILD_REVIEW(1,"子评论");
    private final Integer code;
    private final String value;

}
