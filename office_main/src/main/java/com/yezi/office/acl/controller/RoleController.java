package com.yezi.office.acl.controller;


import com.yezi.office.acl.pojo.para.RoleInfo;
import com.yezi.office.acl.pojo.para.UserRoleUpdateInfo;
import com.yezi.office.acl.pojo.vo.RoleVo;
import com.yezi.office.acl.pojo.vo.UserRoleVo;
import com.yezi.office.acl.service.RoleService;
import com.yezi.office.acl.service.UserRoleService;
import com.yezi.office.service.UserService;
import com.yezi.office.utils.R;
import io.swagger.annotations.ApiOperation;
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
    public R getAllRoles(){
        List<RoleVo> roleList = roleService.listVo();

        return R.ok().data("roleList",roleList);
    }

    @GetMapping("getUserRoleList")
    public R getUserRole(){

        List<UserRoleVo> userRoleVoList = userService.getUserRoleList();

        return R.ok().data("userRoleList",userRoleVoList);
    }

    @GetMapping("getRoleIdList/{id}")
    public R getRoleIdList(@PathVariable("id") String userId){
        List<String> roleIdList = userRoleService.listRoleIdByUserId(userId);

        return R.ok().data("roleIdList",roleIdList);
    }

    @ApiOperation("修改用户角色")
    @PostMapping("updateUserRole")
    public R updateUserRole(@RequestBody UserRoleUpdateInfo info){
        boolean rs = userRoleService.updateUserRole(info);

        return R.ok();
    }

    @PostMapping("createRole")
    public R createRole(@RequestBody RoleInfo roleInfo){

        boolean rs = roleService.createRole(roleInfo);

        return R.ok();
    }

    @PostMapping("updateRole")
    public R updateRole(@RequestBody RoleInfo roleInfo){

        boolean rs = roleService.updateRole(roleInfo);

        return R.ok();
    }
}

