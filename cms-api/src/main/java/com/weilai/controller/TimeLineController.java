package com.weilai.controller;

import com.weilai.common.Result;
import com.weilai.request.QueryModuleParamsRequest;
import com.weilai.request.QueryTypeParamsRequest;
import com.weilai.service.ModuleService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.weilai.enums.TimeLineEnum.FINISH_STATUS;

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

    @ApiOperation("删除标签")
    @PostMapping("/delete")
    public Result delTag(@RequestBody List<Long> ids){
        moduleService.removeBatchByIds(ids);
        return Result.ok("删除成功");
    }

    @ApiOperation("更新状态")
    @PostMapping("/updateStatus")
    public Result updateStatus(@RequestBody Long id){
        moduleService.update().eq("id",id).set("module_type",FINISH_STATUS.getCode());
        return Result.ok("更新成功");
    }


}
