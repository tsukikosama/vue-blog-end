package com.weilai.cms.controller;

import com.weilai.system.common.Result;
import com.weilai.system.config.FileUploadConfig;
import com.weilai.system.exception.CustomException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/common/file")
@Tag(name = "文件上传接口", description = "文件上传接口")
@RequiredArgsConstructor
public class FileController {

    private final FileUploadConfig fileUploadConfig;
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()){
            throw new CustomException("文件不能为空");
        }
        String fileName = file.getOriginalFilename();
        File dest = new File(fileUploadConfig.getLocalUrl() + fileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            return Result.fail("文件上传失败");
        }
        return Result.ok(fileUploadConfig.getUrl()+fileName);
    }
}
