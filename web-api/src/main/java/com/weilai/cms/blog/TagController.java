package com.weilai.cms.blog;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weilai.blog.model.entity.TagEntity;
import com.weilai.blog.model.req.TagReq;
import com.weilai.blog.service.ITagService;
import com.weilai.system.common.Result;
import com.weilai.system.model.resp.TagResp;
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
public class TagController {
    private final ITagService tagService;

    @GetMapping("/page")
    @Operation(summary = "标签分页", description = "标签分页")
    public Result<Page<TagResp>> page(){
        return Result.ok();
    }

    @GetMapping("/list")
    @Operation(summary = "列表",description = "列表")
    public Result<List<TagResp>> list(){
        List<TagEntity> list = tagService.list();
        List<TagResp> tagResp = BeanUtil.copyToList(list, TagResp.class);
        return Result.ok(tagResp);
    }

//    @DeleteMapping("/delete")
//    @Operation(summary = "删除列表",description = "删除列表")
//    public Result<Void> delete(@RequestParam("ids") Long[] ids){
//
//    }

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
