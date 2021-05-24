package com.example.portfolio.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:application.yml"})
@ConfigurationProperties(prefix = "aliyun")
@Data
@Accessors(chain = true)
public class AliyunOssConfig {
    private String endPoint;// 地域节点
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;// OSS的Bucket名称
    private String urlPrefix;// Bucket 域名
    private String fileHost;// 目标文件夹


    /**
     * 将OSSClient放入spring上下文中
     * @return
     */
    @Bean
    public OSS OSSClient(){
        return new OSSClient(endPoint,accessKeyId,getAccessKeySecret());
    }

}
