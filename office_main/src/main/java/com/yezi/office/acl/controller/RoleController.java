package com.yezi.office.acl.controller;


import com.yezi.office.acl.pojo.para.RoleInfo;
import com.yezi.office.acl.pojo.para.UserRoleUpdateInfo;
import com.yezi.office.acl.pojo.vo.RoleVo;
import com.yezi.office.acl.pojo.vo.UserRoleVo;
import com.yezi.office.acl.service.RoleService;
import com.yezi.office.acl.service.UserRoleService;
import com.yezi.office.service.UserService;
import com.yezi.office.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author 叶子
 * @since 2021-01-04
 */
@Api(tags = "角色控制接口")
@RestController
@RequestMapping("/office/acl/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("list")
    @ApiOperation("获取角色列表")
    public R getAllRoles(){
        List<RoleVo> roleList = roleService.listVo();

        return R.ok().data("roleList",roleList);
    }

    @GetMapping("getUserRoleList")
    @ApiOperation("获取用户信息 + 角色ID列表")
    public R getUserRole(){

        List<UserRoleVo> userRoleVoList = userService.getUserRoleList();

        return R.ok().data("userRoleList",userRoleVoList);
    }

    @GetMapping("getRoleIdList/{id}")
    @ApiOperation("根据用户ID获取角色ID列表")
    public R getRoleIdList(@ApiParam("用户ID") @PathVariable("id") String userId){
        List<String> roleIdList = userRoleService.listRoleIdByUserId(userId);

        return R.ok().data("roleIdList",roleIdList);
    }

    @ApiOperation("根据用户ID修改用户拥有的角色信息")
    @PostMapping("updateUserRole")
    public R updateUserRole(@ApiParam("用户ID + 角色ID列表") @RequestBody UserRoleUpdateInfo info){
        boolean rs = userRoleService.updateUserRole(info);

        return R.ok();
    }

    @PostMapping("createRole")
    @ApiOperation("创建新的角色")
    public R createRole(@ApiParam("角色信息 + 角色拥有的菜单权限") @RequestBody RoleInfo roleInfo){

        boolean rs = roleService.createRole(roleInfo);

        return R.ok();
    }

    @PostMapping("updateRole")
    @ApiOperation("根据角色ID修改角色信息 包括角色拥有的菜单权限")
    public R updateRole(@ApiParam("角色信息 + 角色拥有的菜单权限") @RequestBody RoleInfo roleInfo){

        boolean rs = roleService.updateRole(roleInfo);

        return R.ok();
    }
}

