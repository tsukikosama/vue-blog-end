package com.weilai.module.request;

import com.weilai.common.PageQuery;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryReviewParamsRequest extends PageQuery {
    private Long id;
}
