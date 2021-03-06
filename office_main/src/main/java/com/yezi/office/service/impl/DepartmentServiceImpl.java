package com.yezi.office.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yezi.office.pojo.Department;
import com.yezi.office.mapper.DepartmentMapper;
import com.yezi.office.pojo.User;
import com.yezi.office.pojo.para.Query;
import com.yezi.office.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yezi.office.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 叶子
 * @since 2020-12-29
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public Map<String, Object> pageList(Query query) {
        Page<Department> departPage = new Page<>(query.getPageIndex(),query.getPageSize());
        QueryWrapper<Department> wrapper = null;
        if (!StrUtil.isEmpty(query.getDepartName())){
            wrapper = new QueryWrapper<>();
            wrapper.like("depart_name",query.getDepartName());
        }

        Page<Department> page = page(departPage, wrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("departList",page.getRecords());
        map.put("pageTotal",page.getTotal());

        return map;
    }

    @Transactional
    @Override
    public boolean deleteById(String departId) {
        UpdateWrapper<User> userWrapper = new UpdateWrapper<>();
        userWrapper.eq("user_dapartment_id",departId);

        userService.remove(userWrapper);
        removeById(departId);

        return true;
    }
}
