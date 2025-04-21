package com.weilai.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReviewResponse {
    private Integer id;
    private String content;
    private LocalDateTime createTime;
    private Integer likes;
    private Long replyId;
    private Long userId;
    private String nickname;
    private Integer reviewType;
    private String avatar;
    private List<ReviewResponse> replyList;
}
