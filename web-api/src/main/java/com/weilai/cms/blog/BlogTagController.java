package com.weilai.cms.blog;

import cn.hutool.core.bean.BeanUtil;
import com.weilai.blog.model.entity.BlogTagEntity;
import com.weilai.blog.model.entity.TagEntity;
import com.weilai.blog.model.query.TagQueryReq;
import com.weilai.blog.model.req.TagReq;
import com.weilai.blog.model.resp.TagResp;
import com.weilai.blog.service.ITagService;
import com.weilai.blog.service.impl.BlogTagServiceImpl;
import com.weilai.system.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/admin/tag")
@Tag(name = "标签接口", description = "标签接口")
@RequiredArgsConstructor
public class BlogTagController {
    private final BlogTagServiceImpl blogTagService;

    private final ITagService tagService;

    @GetMapping("/page")
    @Operation(summary = "分页", description = "分页")
    public Result<TagResp> page(TagQueryReq req){
        return Result.ok();
    }

    @GetMapping("/list")
    @Operation(summary = "查询标签", operationId = "查询标签")
    public Result<List<TagResp>> list(){
        List<BlogTagEntity> list = blogTagService.list();
        List<TagResp> tag = BeanUtil.copyToList(list, TagResp.class);
        return Result.ok(tag);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除标签", operationId = "删除标签")
    public Result<Void> delete(@RequestParam("ids") List<Long> ids){
        tagService.removeBatchByIds(ids);
        return Result.ok();
    }

    @PutMapping("/update")
    @Operation(summary = "删除标签", operationId = "删除标签")
    public Result<Void> update(@RequestBody TagReq req){
        TagEntity tagEntity = BeanUtil.copyProperties(req, TagEntity.class);
        tagService.updateById(tagEntity);
        return Result.ok();
    }

    @PostMapping("/save")
    @Operation(summary = "保存标签", operationId = "保存标签")
    public Result<Void> save(@RequestBody TagReq req){
        TagEntity tagEntity = BeanUtil.copyProperties(req, TagEntity.class);
        tagService.save(tagEntity);
        return Result.ok();
    }
}
