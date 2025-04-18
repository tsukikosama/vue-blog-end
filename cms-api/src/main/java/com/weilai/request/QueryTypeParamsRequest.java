package com.weilai.request;

import com.weilai.common.PageQuery;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class QueryTypeParamsRequest extends PageQuery {
    @NotNull
    private String tagName;
}
