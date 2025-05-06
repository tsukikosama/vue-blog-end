package com.weilai.request;

import com.weilai.common.PageQuery;
import lombok.Data;

@Data
public class QueryReviewParamsRequest extends PageQuery {
    private Long id;
}
