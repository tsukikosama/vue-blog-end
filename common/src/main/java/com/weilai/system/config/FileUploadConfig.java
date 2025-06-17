package com.weilai.system.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "file")
public class FileUploadConfig {
    private String url;
    private String localUrl;
}
