package com.yezi.office.utils;

import java.util.UUID;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.utils
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2020/12/29 星期二 17:26
 */
public class StringUtils {

    public static String getId(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
