package com.weilai.system.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TimeLineEnum  {
    FINISH_STATUS(0,"已完成"),
    DEVELOP_STATUS(1,"开发中"),
    WAIT_STATUS(2,"待开发");
    private final Integer code;
    private final String value;
    public static String getValue(Integer code) {
        for (TimeLineEnum c : TimeLineEnum.values()) {
            if (c.getCode().equals(code)) {
                return c.getValue();
            }
        }
        return null;
    }
    public static Integer getCode(String value) {
        for (TimeLineEnum c : TimeLineEnum.values()) {
            if (c.getValue().equals(value)) {
                return c.getCode();
            }
        }
        return null;
    }
}
