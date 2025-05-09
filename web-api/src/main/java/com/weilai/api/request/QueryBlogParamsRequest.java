package com.weilai.api.request;

import com.weilai.common.PageQuery;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryBlogParamsRequest extends PageQuery {
    private String tagId;
    private String key;
}
