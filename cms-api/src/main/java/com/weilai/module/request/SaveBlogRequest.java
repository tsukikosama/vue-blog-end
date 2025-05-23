package com.weilai.module.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class SaveBlogRequest {
    private Long id;
    @NotNull(message = "标题不能为空")
    private String title;
    @NotNull(message = "首图不能为空")
    private String picture;
    @NotNull(message = "标签不能为空")
    private List<Long> tagId;
    @NotNull(message = "内容不能为空")
    private String content;
}
