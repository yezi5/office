package com.yezi.office.acl.service;

import com.yezi.office.acl.pojo.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
