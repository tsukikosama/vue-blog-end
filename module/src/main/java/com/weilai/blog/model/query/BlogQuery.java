package com.weilai.blog.model.query;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.weilai.system.annotation.Query;
import com.weilai.system.common.CommonPage;
import com.weilai.system.common.CommonQuery;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BlogQuery extends CommonPage {
    @Query(type = Query.Type.EQ)
    private String title;
    @Query(type = Query.Type.EQ)
    private String nickname;
    @Query(type = Query.Type.IN)
    private String tagId;
    @Query(type = Query.Type.EQ)
    private Integer status;
    @Query(type = Query.Type.BETWEEN)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime[] createTime;
}
