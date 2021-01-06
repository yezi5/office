package com.yezi.office.acl.service.impl;

import cn.hutool.core.util.StrUtil;
import com.yezi.office.acl.pojo.Menu;
import com.yezi.office.acl.pojo.vo.MenuForAclVo;
import com.yezi.office.acl.pojo.vo.MenuVo;
import com.yezi.office.acl.service.RoleMenuService;
import com.yezi.office.acl.service.UserRoleService;
import com.yezi.office.mapper.MenuMapper;
import com.yezi.office.acl.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author 叶子
 * @since 2021-01-04
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<MenuVo> listByAuther(String userId) {
        // 1. 获取用户角色ID列表
        List<String> roleIdList = userRoleService.listRoleIdByUserId(userId);
        // 2. 根据用户角色ID列表，获取菜单ID列表
        List<String> menuIdList = roleMenuService.listMenuIdByRoleIds(roleIdList);
        // 3. 根据菜单ID列表获取菜单列表
        List<Menu> menuList = listByIds(menuIdList);
        // @TODO 4. 构建菜单
        List<MenuVo> menuPageList = buildPage(menuList);

        return menuPageList;
    }

    @Override
    public List<MenuForAclVo> treeMenu() {
        // 1. 获取所有的菜单
        List<Menu> menuList = list();
        // 2. 构建树形结构
        List<MenuForAclVo> menuTree = buildTree(menuList);

        return menuTree;
    }

    @Override
    public List<String> listIdsByRoleId(String roleId) {
        List<String> menuIdList = roleMenuService.listMenuIdByRoleId(roleId);

        return menuIdList;
    }

    private List<MenuForAclVo> buildTree(List<Menu> menuList) {
        List<MenuForAclVo> menuTreeList = new ArrayList<>();
        Map<String, Menu> map = new HashMap<>();
        Map<String, MenuForAclVo> menuTreeMap = new HashMap<>();

        for (Menu menu : menuList) {
            map.put(menu.getMenuId(),menu);
        }
        for (Menu menu : menuList) {
            // 1. 构建当前菜单组件（子菜单组件）
            MenuForAclVo menuChild = new MenuForAclVo();
            BeanUtils.copyProperties(menu,menuChild);
            // 2. 判断当前菜单组件是否有父级菜单
            String menuParentId = menu.getMenuParentId();
            /**
             * 当前菜单有父级菜单
             *
             * 注意：
             *      这种情况下，不要再将当前菜单加入map集合了
             */
            if (!StrUtil.isEmpty(menuParentId)){
                // 1. 获取父级菜单
                Menu parent = getById(menuParentId);



                /**
                 * 3. 判断Map集合中是否有这个菜单
                 *  有的话，只需要填充该菜单对象即可，不需要创建新的菜单，否则会重复
                 */
                // 有 的情况
                if (menuTreeMap.containsKey(menuParentId)){
                    MenuForAclVo menuParent = menuTreeMap.get(menuParentId);

                    // 父级菜单子菜单组件为空
                    if (menuParent.getSubs() ==null){
                        List<MenuForAclVo> subs = new ArrayList<>();
                        subs.add(menuChild);
                        menuParent.setSubs(subs);
                    }else {// 父级菜单子菜单组件 不为空
                        List<MenuForAclVo> subs = menuParent.getSubs();
                        subs.add(menuChild);
                        menuParent.setSubs(subs);
                    }
                    // 重新构建的父级菜单替换原来的父级菜单
                    menuTreeMap.replace(menuParentId,menuParent);
                }else { // 没有 的情况
                    // 构建父级菜单组件
                    MenuForAclVo menuParent = new MenuForAclVo();
                    BeanUtils.copyProperties(parent,menuParent);

                    // 将子菜单组件组合进父菜单组件
                    List<MenuForAclVo> subs = new ArrayList<>();
                    subs.add(menuChild);
                    menuParent.setSubs(subs);

                    // 将父级菜单添加到Map集合
                    menuTreeMap.put(menuParentId,menuParent);
                }
            }else {
                /**
                 * 当前菜单 没有 父级菜单
                 *
                 * 判断当前菜单是否已经在map集合中了
                 *      是   不用处理
                 *      不是  构建并添加到map集合中
                 */
                if (!menuTreeMap.containsKey(menu.getMenuId())){
                    menuTreeMap.put(menu.getMenuId(),menuChild);
                }
            }
        }
        // 这步操作主要是为了保证首页在第一位。
        MenuForAclVo home = menuTreeMap.get("ecb2af8eaefc45bbbe2fc57b74018ad1");
        menuTreeList.add(home);
        for (String key : menuTreeMap.keySet()) {
            if (!"ecb2af8eaefc45bbbe2fc57b74018ad1".equals(key)){
                menuTreeList.add(menuTreeMap.get(key));
            }
        }

        return menuTreeList;
    }

    public List<MenuVo> buildPage(List<Menu> menuList){
        List<MenuVo> menuPageList = new ArrayList<>();
        Map<String, Menu> map = new HashMap<>();
        Map<String, MenuVo> menuVoMap = new HashMap<>();

        for (Menu menu : menuList) {
            map.put(menu.getMenuId(),menu);
        }
        for (Menu menu : menuList) {
            String menuParentId = menu.getMenuParentId();
            /**
             * 当前菜单有父级菜单
             *
             * 注意：
             *      这种情况下，不要再将当前菜单加入map集合了
             */
            if (!StrUtil.isEmpty(menuParentId)){
                // 1. 获取父级菜单
                Menu parent = getById(menuParentId);

                // 2. 构建子菜单组件
                MenuVo menuVoChild = new MenuVo();
                BeanUtils.copyProperties(menu,menuVoChild);
                menuVoChild.setIndex(menu.getMenuIndex());

                /**
                 * 3. 判断Map集合中是否有这个菜单
                 *  有的话，只需要填充该菜单对象即可，不需要创建新的菜单，否则会重复
                 */
                // 有 的情况
                if (menuVoMap.containsKey(menuParentId)){
                    MenuVo menuVoParent = menuVoMap.get(menuParentId);

                    // 父级菜单子菜单组件为空
                    if (menuVoParent.getSubs() ==null){
                        List<MenuVo> subs = new ArrayList<>();
                        subs.add(menuVoChild);
                        menuVoParent.setSubs(subs);
                    }else {// 父级菜单子菜单组件 不为空
                        List<MenuVo> subs = menuVoParent.getSubs();
                        subs.add(menuVoChild);
                        menuVoParent.setSubs(subs);
                    }
                    // 重新构建的父级菜单替换原菜单
                    menuVoMap.replace(menuParentId,menuVoParent);
                }else { // 没有 的情况
                    // 构建父级菜单组件
                    MenuVo menuVoParent = new MenuVo();
                    BeanUtils.copyProperties(parent,menuVoParent);
                    menuVoParent.setIndex(parent.getMenuIndex());

                    // 将子菜单组件组合进父菜单组件
                    List<MenuVo> subs = new ArrayList<>();
                    subs.add(menuVoChild);
                    menuVoParent.setSubs(subs);

                    // 将父级菜单添加到Map集合
                    menuVoMap.put(menuParentId,menuVoParent);
                }
            }else {
                /**
                 * 当前菜单 没有 父级菜单
                 *
                 * 判断当前菜单是否已经在map集合中了
                 *      是   不用处理
                 *      不是  构建并添加到map集合中
                 */
                if (!menuVoMap.containsKey(menu.getMenuId())){
                    MenuVo menuVo = new MenuVo();
                    BeanUtils.copyProperties(menu,menuVo);
                    menuVo.setIndex(menu.getMenuIndex());
                    menuVoMap.put(menu.getMenuId(),menuVo);
                }
            }
        }
        // 这步操作主要是为了保证首页在第一位。
        MenuVo home = menuVoMap.get("ecb2af8eaefc45bbbe2fc57b74018ad1");
        menuPageList.add(home);
        for (String key : menuVoMap.keySet()) {
            if (!"ecb2af8eaefc45bbbe2fc57b74018ad1".equals(key)){
                menuPageList.add(menuVoMap.get(key));
            }
        }

        return menuPageList;
    }
}
