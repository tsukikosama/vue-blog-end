package com.miku.common;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.NioUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.annotation.MultipartConfig;
import java.io.*;
import java.lang.annotation.ElementType;
import java.util.List;

@RestController
@RequestMapping("/upload")
public class upload {

    //读取上传文件的地址
    @Value("${miku.uploadurl}")
    private String url;
    /**
     * 这是图片上传的公共主键
     */
    @PostMapping
    public Result uploadImg(@RequestParam("file") MultipartFile file){
        //获取图片名称
        String name = file.getOriginalFilename();
        //获取图片后缀
        String suffix = FileNameUtil.extName(name);
//        InputStream inputStream = file.getInputStream();
        //重命名图片
        String newFileName = IdUtil.randomUUID();
        newFileName = newFileName+"."+suffix;
        try {
            //把文件写入对应的位置
            file.transferTo(new File(url,newFileName));
        }catch (IOException e){
            throw new RuntimeException("文件上传异常");
        }

        return Result.ok(url+newFileName);
    }

}

