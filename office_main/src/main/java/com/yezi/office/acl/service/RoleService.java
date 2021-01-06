package com.yezi.office.acl.service;

import com.yezi.office.acl.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yezi.office.acl.pojo.para.RoleInfo;
import com.yezi.office.acl.pojo.vo.RoleVo;

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

    /**
     * 获取角色列表
     * @return
     */
    List<RoleVo> listVo();

    /**
     * 根据用户ID获取角色列表
     * @param userId
     * @return
     */
    List<RoleVo> listVo(String userId);

    /**
     * 根据给定的角色信息添加记录到数据库
     * 给定的角色信息包括：
     *      1. 角色基本信息
     *      2. 角色拥有的菜单权限ID
     *
     * @param roleInfo
     * @return
     */
    boolean createRole(RoleInfo roleInfo);

    /**
     * 根据给定的角色信息修改数据库记录
     * 给定的角色信息包括：
     *      1. 角色基本信息
     *      2. 角色拥有的菜单权限ID
     * @param roleInfo
     * @return
     */
    boolean updateRole(RoleInfo roleInfo);
}
