package com.yezi.office.utils;

import cn.hutool.crypto.SecureUtil;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.utils
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2020/12/29 星期二 17:24
 */
public class PassWorldUtils {

    public String encode(String password){
        return SecureUtil.md5(password);
    }
}
