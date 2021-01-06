package com.yezi.office.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yezi.office.acl.pojo.UserRole;
import com.yezi.office.acl.pojo.para.UserRoleUpdateInfo;
import com.yezi.office.mapper.UserRoleMapper;
import com.yezi.office.acl.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public boolean updateUserRole(UserRoleUpdateInfo info) {
        List<String> roleIdList = info.getRoleIdList();
        String userId = info.getUserId();
        // 1. 删除原先拥有，但现在被取消授权的角色
        deleteUserRoleNoInRoleIds(info.getUserId(),roleIdList);
        // 2. 构建指定 用户角色列表
        List<UserRole> userRoleList = new ArrayList<>();
        for (String roleId : roleIdList) {
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(userId);
            userRole.setUserRoleStatu(1);

            userRoleList.add(userRole);
        }
        // 3. 修剪 用户角色列表，去除数据库中已经存在的记录
        userRoleList = trim(userRoleList);
        // 4. 添加这些记录到数据库
        saveBatch(userRoleList);

        return true;
    }

    @Override
    public boolean deleteUserRoleNoInRoleIds(String userId, List<String> roleIds) {
        UpdateWrapper<UserRole> wrapper = new UpdateWrapper();
        wrapper.eq("user_id",userId);
        wrapper.notIn("role_id",roleIds);

        boolean remove = remove(wrapper);

        return remove;
    }

    @Override
    public List<UserRole> trim(List<UserRole> userRoleList) {
        List<UserRole> newList = new ArrayList<>();

        /**
         * 思路：
         *      不能使用记录ID，因为给定列表不存在ID
         *
         *      用户ID和角色ID同时存在，唯一标识一条记录，
         *      所以使用这两个进行操作
         */
        for (UserRole userRole : userRoleList) {
            QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id",userRole.getUserId());
            wrapper.eq("role_id",userRole.getRoleId());

            // 说明数据库中没有这条记录，无需修剪
            if (count(wrapper) == 0){
                newList.add(userRole);
            }
        }

        return newList;
    }
}
