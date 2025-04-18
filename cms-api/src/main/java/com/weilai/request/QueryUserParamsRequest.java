package com.weilai.request;

import com.weilai.common.PageQuery;
import lombok.Data;

@Data
public class QueryUserParamsRequest extends PageQuery {
    private String nickname;
    private String email;
    private String ban;
    private String userType;
}
