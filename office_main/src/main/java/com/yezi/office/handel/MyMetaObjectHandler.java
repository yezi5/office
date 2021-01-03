package com.yezi.office.handel;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        // 自动填充创建时间、修改时间
        this.setFieldValByName("gmtCreate", new Date(), metaObject);
        this.setFieldValByName("gmtModified", new Date(), metaObject);
        this.setFieldValByName("bulletinIsactive", 1, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 自动填充修改时间
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }


}