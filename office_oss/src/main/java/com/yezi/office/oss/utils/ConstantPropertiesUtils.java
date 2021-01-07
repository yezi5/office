package com.yezi.office.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.oss.utils
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2020/12/30 星期三 19:11
 */
@Component
public class ConstantPropertiesUtils implements InitializingBean {

    private final String endpoint = "请使用你个人的账号";

    private final String keyId = "请使用你个人的账号";

    private final String keySecret = "请使用你个人的账号";

    private final String bucketName = "请使用你个人的账号";

    public static String END_POINT;

    public static String ACCESS_KEY_ID;

    public static String ACCESS_KEY_SECRET;

    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;

        ACCESS_KEY_ID = keyId;

        ACCESS_KEY_SECRET = keySecret;

        BUCKET_NAME = bucketName;
    }
}
