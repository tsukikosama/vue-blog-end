package com.weilai.request;

import com.weilai.common.PageQuery;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QueryReviewParamsRequest extends PageQuery {
    private Long id;
}
