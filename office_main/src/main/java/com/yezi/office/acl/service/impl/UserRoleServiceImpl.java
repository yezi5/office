package com.yezi.office.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yezi.office.acl.pojo.UserRole;
import com.yezi.office.mapper.UserRoleMapper;
import com.yezi.office.acl.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author 叶子
 * @since 2021-01-04
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public List<String> listRoleIdByUserId(String userId) {
        List<String> roleIdList = new ArrayList<>();
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<UserRole> userRoles = list(wrapper);

        for (UserRole userRole : userRoles) {
            roleIdList.add(userRole.getRoleId());
        }

        return roleIdList;
    }
}
