package com.weilai.system.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "标签信息")
public class TagResp {
    private Long id;
    private String tagName;
}
