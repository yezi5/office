package com.yezi.office.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
}
