package com.weilai.module.controller;

import com.weilai.common.Result;
import com.weilai.module.entity.CodeEntity;
import com.weilai.module.service.ICodeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author miku
 * @since 2025-05-12
 */
@RestController
@RequestMapping("/code")
@AllArgsConstructor
public class CodeController {
    private final ICodeService iCodeService;

    @GetMapping("/list")
    public Result list(){
        List<CodeEntity> list = iCodeService.list();
        //获取全部的
       return Result.ok(list);

    }
    @PostMapping("/save")
    public Result save(@RequestBody CodeEntity request){
        iCodeService.save(request);
        return Result.ok("保存成功");
    }
}
