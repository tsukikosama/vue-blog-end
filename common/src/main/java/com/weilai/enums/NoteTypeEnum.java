package com.weilai.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NoteTypeEnum {
    MAIN_REVIEW(1,"记仇日记"),
    CHILD_REVIEW(2,"普通日记");
    private final Integer code;
    private final String value;
}
