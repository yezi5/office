package com.yezi.office.acl.service;

import com.yezi.office.acl.pojo.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yezi.office.acl.pojo.para.UserRoleUpdateInfo;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author 叶子
 * @since 2021-01-04
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 根据用户ID获取角色ID列表
     * @param userId
     * @return
     */
    List<String> listRoleIdByUserId(String userId);

    /**
     * 修改用户拥有的角色
     * @param info
     * @return
     */
    boolean updateUserRole(UserRoleUpdateInfo info);

    /**
     * 删除不在给定角色ID列表中的记录
     *
     * 比如：
     *      给定角色ID列表为：A B C
     * 那么角色ID为 X 的记录就要被删除
     * @param roleIds
     * @return
     */
    boolean deleteUserRoleNoInRoleIds(String userId,List<String> roleIds);

    /**
     * 修剪给定数组
     *
     * 删除数组中 已经存在于数据库 的记录
     * 返回修剪后的数组
     * @param userRoleList
     * @return
     */
    List<UserRole> trim(List<UserRole> userRoleList);
}
