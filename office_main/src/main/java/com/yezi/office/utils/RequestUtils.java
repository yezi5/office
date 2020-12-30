package com.yezi.office.utils;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.utils
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2020/12/30 星期三 16:24
 */
public class RequestUtils {

    public static String getAddress(String url){
        String rs = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = client.newCall(request);
        Response response = null;
        try {
            response = call.execute();
            if (response.isSuccessful()){
                rs = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
