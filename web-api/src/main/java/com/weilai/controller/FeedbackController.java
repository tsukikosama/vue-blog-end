package com.weilai.controller;

import com.weilai.common.PageQuery;
import com.weilai.common.Result;
import com.weilai.entity.Feedback;
import com.weilai.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService service;
    @GetMapping("/all")
    public Result getAll(){
        List<Feedback> list = service.list();
        return Result.ok(list);
    }
    @PostMapping("/add")
    public Result addFeedBack(@RequestBody Feedback feedback){

        boolean save = service.save(feedback);
        if(!save){
            return Result.fail("添加失败");
        }
        return Result.ok("添加成功");
    }

    @PostMapping("/del")
    public Result del(@RequestParam("id") Integer id){
        service.removeById(id);
        return Result.ok("删除成功");
    }

    @GetMapping("/list")
    public Result getBlogByPage(PageQuery query){
        System.out.println(query);
        return Result.ok(service.listByPage(query));
    }

}
