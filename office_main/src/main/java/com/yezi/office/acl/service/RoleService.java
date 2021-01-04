package com.yezi.office.acl.service;

import com.yezi.office.acl.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author 叶子
 * @since 2021-01-04
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据用户ID获取用户角色名列表
     * @param userId
     * @return
     */
    List<String> roleNameList(String userId);

}
