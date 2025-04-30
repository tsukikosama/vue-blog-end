package com.weilai.request;

import lombok.Data;

@Data
public class ReplyRequest {
    private Long replyId;
    private String content;
    private Long blogId;
}
