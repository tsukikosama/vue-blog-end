package com.weilai.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BlogRecordResponse {
    private Long id;
    private String title;
    private String picture;
    private String content;
    private LocalDateTime createDate;
    private String tagId;
    private Long visit;
    private Long userId;
    private String nickname;
    private String avatar;
}
