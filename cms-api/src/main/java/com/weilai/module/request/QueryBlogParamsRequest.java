package com.weilai.module.request;

import com.weilai.common.PageQuery;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class QueryBlogParamsRequest extends PageQuery {
    private Long tagId;
    private String title;
    private String startTime;
    private String endTime;
    private String userId;
}
