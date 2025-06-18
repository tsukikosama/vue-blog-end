package com.weilai.cms.blog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weilai.blog.model.resp.BlogResp;
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
@RequestMapping("/admin/blog")
@Tag(name = "博客接口", description = "博客接口")
@RequiredArgsConstructor
public class BlogController {
    @GetMapping("/page")
    @Operation(summary = "博客分页", description = "博客分页接口")
    public Result<Page<BlogResp>> page(){
        return Result.ok();
    }
}
