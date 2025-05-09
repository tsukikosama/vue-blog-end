package com.weilai.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BlogRecordResponse {
    private Long id;
    private String title;
    private String picture;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createDate;
    private String tagId;
    private Long visit;
    private Long userId;
    private String nickname;
    private String avatar;
    private Long likes;
    private Integer isLike;
}
