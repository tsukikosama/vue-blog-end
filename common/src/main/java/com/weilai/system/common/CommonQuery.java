package com.weilai.system.common;

import com.weilai.system.annotation.Query;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CommonQuery {
    @Query(type = Query.Type.BETWEEN,column = "create_time")
    private LocalDateTime[] createTime;
    @Query(type = Query.Type.EQ)
    private Long id;
}
