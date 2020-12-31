package com.yezi.office.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.jdbc.StringUtils;
import com.yezi.office.pojo.Department;
import com.yezi.office.pojo.User;
import com.yezi.office.mapper.UserMapper;
import com.yezi.office.pojo.para.Query;
import com.yezi.office.pojo.vo.UserVo;
import com.yezi.office.service.DepartmentService;
import com.yezi.office.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private DepartmentService departmentService;

    @Override
    public Map<String,Object> pageQuery(Query query) {
        Page<User> userPage = new Page<>(query.getPageIndex(),query.getPageSize());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (!StringUtils.isNullOrEmpty(query.getUsername())){
            wrapper.like("username",query.getUsername());
        }

        List<UserVo> userVoList = new ArrayList<>();
        List<User> userList = page(userPage, wrapper).getRecords();

        for (User user : userList) {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user,userVo);

            QueryWrapper<Department> departmentQueryWrapper = new QueryWrapper<>();
            departmentQueryWrapper.eq("depart_id", user.getUserDapartmentId());
            if (!StringUtils.isNullOrEmpty(query.getDepartName())){
                departmentQueryWrapper.eq("depart_name",query.getDepartName());
            }

            Department depart = departmentService.getOne(departmentQueryWrapper);
            userVo.setCreate(user.getGmtCreate());
            if (depart != null){
                userVo.setDepartName(depart.getDepartName());
                userVoList.add(userVo);
            }
        }

        List<Department> departmentList = departmentService.list();

        Map<String,Object> map = new HashMap<>();
        map.put("userList",userVoList);
        map.put("pageTotal",userPage.getTotal());
        map.put("selectList",departmentList);

        return map;
    }

    @Override
    public Map<String,Object> getUserVoById(String userId) {
        UserVo userVo = new UserVo();
        User user = baseMapper.selectById(userId);
        BeanUtils.copyProperties(user,userVo);

        Department department = departmentService.getById(user.getUserDapartmentId());
        userVo.setDepartName(department.getDepartName());

        Map<String,Object> map = new HashMap<>();
        map.put("userInfo",userVo);

        return map;
    }
}
