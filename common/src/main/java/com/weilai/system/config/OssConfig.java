package com.weilai.system.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "oss")
public class OssConfig {
    private String Domain;
    private String Prefix;
    private String EndPoint;
    private String AccessKeyId;
    private String AccessKeySecret;
    private String BucketName;
}
