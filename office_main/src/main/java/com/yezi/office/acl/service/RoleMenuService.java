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

    /**
     * 根据用户 角色ID 获取 菜单ID列表
     * @param roleId
     * @return
     */
    List<String> listMenuIdByRoleId(String roleId);

    /**
     * 根据 角色ID 和指定的 菜单ID列表 添加 role_menu 记录
     * @param roleId
     * @param menuIdList
     * @return
     */
    boolean save(String roleId, List<String> menuIdList);

    /**
     * 在 role_menu 表中删除同时具有以下特征的记录：
     *  1. 含有指定角色ID
     *  2. 菜单ID不在给定 菜单ID 列表中
     *
     * @param roleId
     * @param menuIdList
     * @return
     */
    boolean deleteNotInMenuIds(String roleId, List<String> menuIdList);

    /**
     * 修剪角色菜单列表，剔除数据库中已经存在的记录
     * @param roleMenuList
     * @return
     */
    List<RoleMenu> trim(List<RoleMenu> roleMenuList);
}
