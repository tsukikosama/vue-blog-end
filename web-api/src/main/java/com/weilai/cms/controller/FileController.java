package com.weilai.cms.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.weilai.system.common.Result;
import com.weilai.system.config.FileUploadConfig;
import com.weilai.system.config.OssConfig;
import com.weilai.system.exception.CustomException;
import com.weilai.system.utils.FileUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

import static com.weilai.system.enums.FileUploadTypeEnum.ALI_YUN;
import static com.weilai.system.enums.FileUploadTypeEnum.LOCAL_STORAGE;

@RestController
@RequestMapping("/common/file")
@Tag(name = "文件上传接口", description = "文件上传接口")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final FileUploadConfig fileUploadConfig;
    private final OssConfig ossConfig;

    private static final Parser parser = Parser.builder().build();
    private static final HtmlRenderer renderer = HtmlRenderer.builder().build();
    @PostMapping("/upload")
    @Operation(summary = "文件上传",description = "文件上传")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()){
            throw new CustomException("文件不能为空");
        }
        String url = "";
        if (fileUploadConfig.getType().equals(LOCAL_STORAGE.getCode())){
            //本地保存
           url = localUpload(file);
        }else if(fileUploadConfig.getType().equals(ALI_YUN.getCode())){
           url = ossUpload(file);
        }
        return Result.ok(url);
    }

    public String localUpload(MultipartFile file){
        //获取文件的后缀类型
        String fileName = FileUtils.createFileName(file.getOriginalFilename());
        String filePath = FileUtils.createFilePath(fileUploadConfig.getLocalUrl());
        File dest = new File(filePath + fileName);
        log.info("文件保存位置:{}",dest.getPath());
        try {
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs(); // 原生方法创建父目录
            }
            file.transferTo(dest);
        } catch (IOException e) {
            throw new CustomException("文件保存失败");
        }
        return fileUploadConfig.getUrl()+DateUtil.today()+"/"+fileName;
    }

    public String ossUpload(MultipartFile file){
        // 创建 OSSClient 实例
        OSS ossClient = new OSSClientBuilder().build(
                ossConfig.getEndPoint(),
                ossConfig.getAccessKeyId(),
                ossConfig.getAccessKeySecret());
        try {
            String fileName = FileUtils.createFileName(file.getOriginalFilename());
            // 上传文件流
            ossClient.putObject(ossConfig.getBucketName(), fileName, file.getInputStream());
            // 构建访问 URL
            String url = "https://" + ossConfig.getBucketName() + "." +
                    ossConfig.getEndPoint() + "/" + fileName;
            log.info("上传成功: {}", url);
            return url;
        } catch (Exception e) {
            log.error("上传 OSS 失败", e);
            throw new CustomException("上传失败");
        } finally {
            ossClient.shutdown();
        }
    }

    @PostMapping("/uploadMd")
    @Operation(summary = "解析md文件",description = "解析md文件")
    public Result<String> uploadMd(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.fail("上传失败：文件不能为空");
        }
        String filename = file.getOriginalFilename();
        if (filename == null || !filename.toLowerCase().endsWith(".md")) {
            return Result.fail("上传失败：请上传 .md 文件");
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            String content = reader.lines().collect(Collectors.joining("\n"));

            // 直接返回 Markdown 原文，不转换
            return Result.ok(content);

        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("读取失败：" + e.getMessage());
        }
    }
    public static String toHtml(String markdownText) {
        return renderer.render(parser.parse(markdownText));
    }
}
