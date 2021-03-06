package com.yezi.office;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author 叶子
 * @Description 主类
 * @PackageName com.yezi.office
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2020/12/29 星期二 14:32
 */
@SpringBootApplication
@MapperScan(basePackages = "com.yezi.office.mapper")
public class OfficeApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                OfficeApplication.class,
                args
        );
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {

        PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();

        c.setIgnoreUnresolvablePlaceholders(true);

        return c;
    }

}
