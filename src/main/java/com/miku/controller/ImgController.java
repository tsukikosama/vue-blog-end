package com.miku.controller;

import com.miku.common.Result;
import com.miku.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("img")
public class ImgController {
    @Autowired
    private ImgService service;
    @GetMapping("/getmgz")
    public Result getMGZ(){
        return  Result.ok(service.list());
    }
}
