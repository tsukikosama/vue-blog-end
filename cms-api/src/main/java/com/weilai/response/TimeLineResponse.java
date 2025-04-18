package com.weilai.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimeLineResponse {
    private Long id;
    private String moduleName;
    private Integer moduleType;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
