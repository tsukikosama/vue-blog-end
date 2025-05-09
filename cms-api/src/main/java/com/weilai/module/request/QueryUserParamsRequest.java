package com.weilai.module.request;

import com.weilai.common.PageQuery;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryUserParamsRequest extends PageQuery {
    private String nickname;
    private String email;
    private String ban;
    private String userType;
}
