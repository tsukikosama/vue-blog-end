package com.weilai.module.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BlogRecordResponse  {
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
