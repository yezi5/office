package com.yezi.office.service;

import com.yezi.office.pojo.Affair;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yezi.office.pojo.para.AffairQuery;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 叶子
 * @since 2021-01-01
 */
public interface AffairService extends IService<Affair> {

    /**
     * 条件查询 + 分页查询
     * @param query
     * @return
     */
    Map<String, Object> find(AffairQuery query);
}
