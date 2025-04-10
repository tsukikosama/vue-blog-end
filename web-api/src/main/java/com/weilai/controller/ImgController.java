package com.weilai.controller;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.IdUtil;
import com.weilai.common.Result;
import com.weilai.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
