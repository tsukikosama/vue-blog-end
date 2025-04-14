package com.weilai.controller;

import cn.hutool.core.date.DateUtil;
import com.weilai.common.CommonQuery;
import com.weilai.common.Result;
import com.weilai.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping("/get")
    public Result getModule(){
        List<Module> list = moduleService.list();
        return Result.ok(list);
    }

    @PostMapping("/add")
    public Result addModule(Module module){
       String msg =  moduleService.savaModule(module);
       return Result.ok(msg);
    }

    @PostMapping("/update")
    public Result updateModule(Module module){
        boolean b = moduleService.saveOrUpdate(module);
        if (!b){
            return Result.ok("更新失败");
        }
        return Result.ok("更新成功");
    }

    @GetMapping("/list")
    public Result getBlogByPage(CommonQuery query){
        System.out.println(query);
        return Result.ok(moduleService.listByPage(query));
    }

    @PostMapping("/del")
    public Result del(@RequestParam("id") Integer id){
        moduleService.removeById(id);
        return Result.ok("删除成功");
    }
}
