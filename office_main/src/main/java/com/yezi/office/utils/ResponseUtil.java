package com.yezi.office.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {

    public static void out(HttpServletResponse response, R r) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        try {
            /**
             * writeValue这个方法会将指定Java数据序列化为json数据，并写入指定的流
             * 注意：这个类不会做flush操作，需要自己手动flush，否则可能会丢失数据！
             */
            mapper.writeValue(response.getWriter(), r);
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
