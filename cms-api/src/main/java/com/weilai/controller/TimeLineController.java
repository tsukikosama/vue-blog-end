package com.weilai.controller;

import com.weilai.common.Result;
import com.weilai.request.QueryModuleParamsRequest;
import com.weilai.request.QueryTypeParamsRequest;
import com.weilai.service.ModuleService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("timeLine")
@RestController
@RequiredArgsConstructor
public class TimeLineController {
    private final ModuleService moduleService;
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public Result page(QueryModuleParamsRequest request){
        return Result.ok(moduleService.page(request));
    }
}
