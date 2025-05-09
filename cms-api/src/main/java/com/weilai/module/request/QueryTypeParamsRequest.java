package com.weilai.module.request;

import com.weilai.common.PageQuery;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class QueryTypeParamsRequest extends PageQuery {
    @NotNull
    private String tagName;
}
