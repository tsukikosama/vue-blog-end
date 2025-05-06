package com.weilai.response;

import cn.hutool.core.date.DateTime;
import lombok.Data;

import java.util.List;

@Data
public class ReviewResponse {
    private Long id;
    private String content;
    private String createTime;
    private Integer likes;
    private Integer replyCount;
    private String avatar;
    private String nickname;
    private List<ReviewResponse> childList;
}
