package com.yezi.office.service;

import com.yezi.office.pojo.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yezi.office.pojo.para.Query;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 叶子
 * @since 2020-12-29
 */
public interface DepartmentService extends IService<Department> {

    /**
     * 分页查询 + 条件查询
     * @param query
     * @return
     */
    Map<String, Object> pageList(Query query);
}
