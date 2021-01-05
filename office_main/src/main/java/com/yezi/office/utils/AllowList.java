package com.yezi.office.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.utils
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2021/1/5 星期二 11:43
 */
public class AllowList {

    private static List<String> allowList = null;

    private static void init(){
        allowList = new ArrayList<>();

        allowList.add("/office/oss/uploadAvatar");
        allowList.add("/userlogin");
        allowList.add("/userlogout");
        allowList.add("/userjwt");
        allowList.add("/v2/api-docs");
        allowList.add("/swagger-resources/configuration/ui");
        allowList.add("/swagger-resources");
        allowList.add("/swagger-resources/configuration/security");
        allowList.add("/swagger-ui.html");
        allowList.add("/css/**");
        allowList.add("/js/**");
        allowList.add("/images/**");
        allowList.add("/webjars/**");
        allowList.add("/import/test");
        allowList.add("**/favicon.ico");
    }

    public static boolean hasElement(String uri){
        init();
        return allowList.contains(uri);
    }
}
