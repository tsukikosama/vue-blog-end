package com.weilai.api.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyRequest {
    private Long replyId;
    private String content;
    private Long blogId;
}
