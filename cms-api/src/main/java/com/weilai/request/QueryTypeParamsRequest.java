package com.weilai.request;

import com.weilai.common.PageQuery;
import lombok.Data;

@Data
public class QueryTypeParamsRequest extends PageQuery {
    private String tagId;

}
