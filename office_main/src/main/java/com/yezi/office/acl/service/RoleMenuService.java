package com.yezi.office.acl.service;

import com.yezi.office.acl.pojo.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *
 * @author 叶子
 * @since 2021-01-04
 */
public interface RoleMenuService extends IService<RoleMenu> {

    /**
     * 根据用户 角色ID列表 获取 菜单ID列表
     * @param roleIdList
     * @return
     */
    List<String> listMenuIdByRoleIds(List<String> roleIdList);
}
