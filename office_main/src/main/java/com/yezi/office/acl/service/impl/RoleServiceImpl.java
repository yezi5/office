package com.yezi.office.acl.service.impl;

import com.yezi.office.acl.pojo.Role;
import com.yezi.office.mapper.RoleMapper;
import com.yezi.office.acl.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yezi.office.acl.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author 叶子
 * @since 2021-01-04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public List<String> roleNameList(String userId) {
        List<String> roleNameList = new ArrayList<>();
        List<String> roleIdList = userRoleService.listRoleIdByUserId(userId);

        List<Role> roles = listByIds(roleIdList);
        for (Role role : roles) {
            roleNameList.add(role.getRoleName());
        }

        return roleNameList;
    }
}
