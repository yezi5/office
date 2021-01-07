package com.yezi.office.oss;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.oss
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2020/12/30 星期三 19:07
 */
@SpringBootApplication(scanBasePackages = "com.yezi.office")
public class OSSApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                OSSApplication.class,
                args
        );
    }

    @Bean
    public MultipartConfigElement multipartConfigElement(@Value("${spring.servlet.multipart.max-file-size}")String maxFileSize, @Value("${spring.servlet.multipart.max-request-size}") String maxRequestSize) {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.parse(maxFileSize));
        factory.setMaxRequestSize(DataSize.parse(maxRequestSize));
        return factory.createMultipartConfig();
    }
}
