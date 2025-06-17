package com.weilai.system.common;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CommonQuery {
    private LocalDateTime[] createTime;
    private Long id;
}
