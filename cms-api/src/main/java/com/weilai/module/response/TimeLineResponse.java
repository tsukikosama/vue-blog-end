package com.weilai.module.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TimeLineResponse {
    private Long id;
    private String moduleName;
    private Integer moduleType;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
