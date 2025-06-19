package com.weilai.blog.model.req;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Data
public class TagReq implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotNull(message = "标签名不能为空")
    private String tagName;
}
