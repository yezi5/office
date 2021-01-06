package com.yezi.office.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.jdbc.StringUtils;
import com.yezi.office.acl.pojo.vo.RoleVo;
import com.yezi.office.acl.pojo.vo.UserRoleVo;
import com.yezi.office.acl.service.RoleService;
import com.yezi.office.acl.service.UserRoleService;
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
    @Resource
    private UserMapper userMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

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
        map.put("pageTotal",userVoList.size());
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

    @Override
    public List<String> listByUserName(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("username",username);
        List<User> userList = baseMapper.selectList(wrapper);

        List<String> idList = new ArrayList<>();

        for (User user : userList) {
            idList.add(user.getUserId());
        }

        return idList;
    }

    @Override
    public User loadByUserName(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        User user = getOne(wrapper);
        return user;
    }

    @Override
    public int refreshToken(String userId, String token) {
        Map<String,String> map = new HashMap<>();
        map.put("userId",userId);
        map.put("token",token);
        int count = userMapper.refreshToken(map);

        return count;
    }

    @Override
    public UserVo getLoginUser(String userId) {
        User user = getById(userId);
        UserVo userInfo = new UserVo();
        BeanUtils.copyProperties(user,userInfo);
        userInfo.setCreate(user.getGmtCreate());

        List<String> roleNameList = roleService.roleNameList(userId);
        userInfo.setRoleNameList(roleNameList);

        return userInfo;
    }

    @Override
    public List<UserRoleVo> getUserRoleList() {
        List<UserRoleVo> userRoleVoList = new ArrayList<>();
        // 1. 获取用户列表
        List<User> userList = list();
        // 2. 构建返回数组
        for (User user : userList) {
            UserRoleVo userRoleVo = new UserRoleVo();
            BeanUtils.copyProperties(user,userRoleVo);
            // 获取部门名
            String departName = departmentService.getById(user.getUserDapartmentId()).getDepartName();
            userRoleVo.setDepartName(departName);
            // 获取创建时间
            userRoleVo.setGmtCreate(user.getGmtCreate());
            // 获取角色ID列表
            List<String> roleIdList = userRoleService.listRoleIdByUserId(user.getUserId());
            userRoleVo.setRoleIdList(roleIdList);
            // 添加到返回集合
            userRoleVoList.add(userRoleVo);
        }

        return userRoleVoList;
    }


}
