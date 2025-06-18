package com.weilai.cms.blog;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weilai.blog.model.entity.BlogEntity;
import com.weilai.blog.model.req.BlogReq;
import com.weilai.blog.model.resp.BlogResp;
import com.weilai.blog.service.IBlogService;
import com.weilai.blog.service.impl.BlogServiceImpl;
import com.weilai.system.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    private final IBlogService blogService;
    @GetMapping("page")
    @Operation(summary = "博客分页", description = "博客分页接口")
    public Result<Page<BlogResp>> page(){
        return Result.ok();
    }

    @PostMapping("save")
    @Operation(summary = "保存博客", description = "保存博客")
    public Result<Void> save(@RequestBody BlogReq req){
        blogService.saveBlog(req);
        return Result.ok();
    }

    @PutMapping("update")
    @Operation(summary = "更新博客", description = "更新博客")
    public Result<Void> update(@RequestBody BlogReq req){
        blogService.updateBlog(req);
        return Result.ok();
    }

    @DeleteMapping("/delete/{ids}")
    @Operation(summary = "删除博客",description = "删除博客")
    public Result<Void> delete(@PathVariable("ids") List<Long> id){
        blogService.removeBlog(id);
        return Result.ok();
    }

}
