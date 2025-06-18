package com.weilai.cms.controller;

import com.weilai.system.common.Result;
import com.weilai.system.model.resp.DictValueResp;
import com.weilai.system.service.ISysDictService;
import com.weilai.system.service.ISysDictValueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/sys/sysDictValueEntity")
@RequiredArgsConstructor
@Tag(name = "字典值接口", description = "字典值接口")
public class SysDictValueController {

    private final ISysDictValueService sysDictService;
    @GetMapping("/{labelName}")
    @Operation(summary = "查询字典", description = "查询字典")
    public Result<List<DictValueResp>> getDict(@PathVariable("labelName") String labelName){
        List<DictValueResp> list = sysDictService.getDictList(labelName);
        return Result.ok(list);
    }
}
