package com.weilai.api.response;

import lombok.Data;

@Data
public class UserMainInfoResponse {
    private Integer weekCount;
    private Integer blogCount;
    private Integer monthCount;
}
