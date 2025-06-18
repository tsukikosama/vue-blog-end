package com.weilai.system.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Schema(description = "部门信息")
public class DeptReq {
    private Long id;
    @NotNull(message = "部门名称不能为空")
    @Schema(description = "部门名称")
    private String name;
    @NotNull(message = "上级部门id不能为空")
    @Schema(description = "父部门id")
    private Long parentId;
    private String description;

}
