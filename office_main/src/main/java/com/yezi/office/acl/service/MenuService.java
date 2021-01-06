package com.yezi.office.acl.service;

import com.yezi.office.acl.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yezi.office.acl.pojo.vo.MenuForAclVo;
import com.yezi.office.acl.pojo.vo.MenuVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author 叶子
 * @since 2021-01-04
 */
public interface MenuService extends IService<Menu> {

    /**
     * 根据用户权限获取用户菜单，构建前端页面
     * @param userId
     * @return
     */
    List<MenuVo> listByAuther(String userId);

    /**
     * 构建权限管理中的菜单树
     * @return
     */
    List<MenuForAclVo> treeMenu();

    /**
     * 根据角色ID获取对应的菜单ID列表
     * @param roleId
     * @return
     */
    List<String> listIdsByRoleId(String roleId);
}
