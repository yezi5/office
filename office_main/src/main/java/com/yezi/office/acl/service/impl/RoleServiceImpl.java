package com.yezi.office.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yezi.office.acl.pojo.Role;
import com.yezi.office.acl.pojo.RoleMenu;
import com.yezi.office.acl.pojo.para.RoleInfo;
import com.yezi.office.acl.pojo.vo.RoleVo;
import com.yezi.office.acl.service.RoleMenuService;
import com.yezi.office.mapper.RoleMapper;
import com.yezi.office.acl.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yezi.office.acl.service.UserRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author 叶子
 * @since 2021-01-04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<String> roleNameList(String userId) {
        List<String> roleNameList = new ArrayList<>();
        List<String> roleIdList = userRoleService.listRoleIdByUserId(userId);

        List<Role> roles = listByIds(roleIdList);
        for (Role role : roles) {
            roleNameList.add(role.getRoleName());
        }

        return roleNameList;
    }


    @Override
    public List<RoleVo> listVo() {
        List<RoleVo> roleVoList = new ArrayList<>();
        List<Role> list = list();

        for (Role role : list) {
            RoleVo roleVo = new RoleVo();
            BeanUtils.copyProperties(role,roleVo);
            roleVo.setCreate(role.getGmtCreate());
            roleVoList.add(roleVo);
        }

        return roleVoList;
    }

    @Override
    public List<RoleVo> listVo(String userId) {
        List<RoleVo> roleVoList = new ArrayList<>();

        // 1. 获取角色ID列表
        List<String> roleIdList = userRoleService.listRoleIdByUserId(userId);
        // 2. 根据角色ID列表获取角色
        List<Role> roleList = listByIds(roleIdList);
        // 3. 封装返回值
        for (Role role : roleList) {
            RoleVo roleVo = new RoleVo();
            BeanUtils.copyProperties(role,roleVo);
            // 获取创建时间
            roleVo.setCreate(role.getGmtCreate());
            // 添加到返回集合
            roleVoList.add(roleVo);
        }


        return roleVoList;
    }

    @Override
    @Transactional
    public boolean createRole(RoleInfo roleInfo) {
        // 1. 构建角色信息
        Role role = new Role();
        BeanUtils.copyProperties(roleInfo,role);
        role.setRoleStatus(1);

        /**
         * 2. 添加角色信息到数据库
         *
         * 注意：这一步必须先执行，因为根据mybatis-plus自动填充属性的特性
         * 当执行过添加方法后，原 角色对象 就有了 ID，可以获取执行下一步操作
         */
        boolean save1 = save(role);
        boolean save2 = false;

        // 3. 根据 角色ID 和指定的 菜单ID列表 添加 role_menu 记录
        if (!roleInfo.getMenuIdList().isEmpty()){
            save2 = roleMenuService.save(role.getRoleId(),roleInfo.getMenuIdList());
        }


        return save1 && save2;
    }

    @Override
    @Transactional
    public boolean updateRole(RoleInfo roleInfo) {
        String roleId = roleInfo.getRoleId();
        List<String> menuIdList = roleInfo.getMenuIdList();
        // 1. 构建角色对象
        Role role = new Role();
        role.setRoleId(roleId);
        role.setRoleStatus(1);
        role.setRoleCode(roleInfo.getRoleCode());
        role.setRoleName(roleInfo.getRoleName());
        // 2. 修改角色数据库记录
        updateById(role);

        // 3. 修改 角色菜单 记录
        // 3.1 删除原先拥有，现在被取消授权的权限
        roleMenuService.deleteNotInMenuIds(roleId,menuIdList);
        // 3.2 构建指定的 角色菜单列表
        List<RoleMenu> roleMenuList = new ArrayList<>();
        for (String menuId : menuIdList) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleMenuStatus(1);
            roleMenuList.add(roleMenu);
        }
        // 3.2 修剪角色菜单列表，剔除数据库中已经存在的记录
        roleMenuList = roleMenuService.trim(roleMenuList);
        // 3.3 将修剪后的角色菜单列表保存到数据库
        boolean rs = roleMenuService.saveBatch(roleMenuList);


        return rs;
    }
}
