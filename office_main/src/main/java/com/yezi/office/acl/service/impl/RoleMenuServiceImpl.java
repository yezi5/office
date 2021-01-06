package com.yezi.office.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yezi.office.acl.pojo.RoleMenu;
import com.yezi.office.mapper.RoleMenuMapper;
import com.yezi.office.acl.service.RoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author 叶子
 * @since 2021-01-04
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Override
    public List<String> listMenuIdByRoleIds(List<String> roleIdList) {
        QueryWrapper<RoleMenu> wrapper = new QueryWrapper<>();
        wrapper.in("role_id",roleIdList);
        List<RoleMenu> roleMenuList = list(wrapper);

        List<String> menuIdList = new ArrayList<>();

        for (RoleMenu roleMenu : roleMenuList) {
            menuIdList.add(roleMenu.getMenuId());
        }

        return menuIdList;
    }

    @Override
    public List<String> listMenuIdByRoleId(String roleId) {
        List<String> roleIdList = new ArrayList<>();
        roleIdList.add(roleId);
        List<String> menuIdList = this.listMenuIdByRoleIds(roleIdList);

        return menuIdList;
    }

    @Override
    public boolean save(String roleId, List<String> menuIdList) {
        // 1. 构建要添加的 纪录列表
        List<RoleMenu> roleMenuList = new ArrayList<>();
        for (String menuId : menuIdList) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleMenuStatus(1);
            roleMenuList.add(roleMenu);
        }
        // 2. 创建记录
        boolean rs = saveBatch(roleMenuList);

        return rs;
    }

    @Override
    public boolean deleteNotInMenuIds(String roleId, List<String> menuIdList) {
        if (!menuIdList.isEmpty()){
            UpdateWrapper<RoleMenu> wrapper = new UpdateWrapper<>();
            wrapper.eq("role_id",roleId);
            wrapper.notIn("menu_id",menuIdList);

            boolean remove = remove(wrapper);
            return remove;
        }

        return true;
    }

    @Override
    public List<RoleMenu> trim(List<RoleMenu> roleMenuList) {
        List<RoleMenu> newList = new ArrayList<>();

        for (RoleMenu roleMenu : roleMenuList) {
            QueryWrapper<RoleMenu> wrapper = new QueryWrapper<>();
            wrapper.eq("role_id",roleMenu.getRoleId());
            wrapper.eq("menu_id",roleMenu.getMenuId());

            if (count(wrapper) == 0){
                newList.add(roleMenu);
            }
        }

        return newList;
    }
}
