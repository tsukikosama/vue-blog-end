package com.weilai.controller;

import com.weilai.common.Result;
import com.weilai.exception.CustomException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${miku.localurl}")
    private String UPLOAD_DIR;

    @Value("${miku.uploadurl}")
    private String VIEW_URL;

    @PostMapping("/upload")
    public Result fileUpload(@RequestParam("file") MultipartFile file){
        // 获取原始文件扩展名
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 使用 UUID 生成唯一文件名
        String randomFileName = UUID.randomUUID().toString().replace("-", "") + suffix;
        // 保存文件
        String filePath = UPLOAD_DIR + randomFileName;
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new CustomException("上传图片失败");
        }
        return Result.ok(VIEW_URL + randomFileName);
    }
}
