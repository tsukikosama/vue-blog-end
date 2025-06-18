package com.weilai.cms.blog;

import com.weilai.blog.model.req.TagQueryReq;
import com.weilai.blog.model.resp.TagResp;
import com.weilai.blog.service.impl.BlogTagServiceImpl;
import com.weilai.system.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author miku
 * @since 2025-06-12
 */
@RestController
@RequestMapping("/admin/tag")
@Tag(name = "标签接口", description = "标签接口")
@RequiredArgsConstructor
public class BlogTagController {
    private final BlogTagServiceImpl blogTypeService;

    @GetMapping("/page")
    @Operation(summary = "分页", description = "分页")
    public Result<TagResp> page(TagQueryReq req){
        return Result.ok();
    }

//    @GetMapping("/list")
//    @Operation(summary = "查询标签", operationId = "查询标签")
//    public Result<>
}
