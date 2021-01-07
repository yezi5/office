package com.yezi.office.acl.controller;


import com.yezi.office.acl.pojo.vo.MenuForAclVo;
import com.yezi.office.acl.pojo.vo.MenuVo;
import com.yezi.office.acl.service.MenuService;
import com.yezi.office.utils.R;
import com.yezi.office.utils.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单 前端控制器
 * </p>
 *
 * @author 叶子
 * @since 2021-01-04
 */
@Api(tags = "菜单管理接口")
@RestController
@RequestMapping("/office/acl/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("buildPage")
    @ApiOperation("根据当前登录用户的权限构建对应的菜单")
    public R buildPage(HttpServletRequest request){
        String userId = TokenUtils.getUserIdByJwtToken(request);

        List<MenuVo> menuList = menuService.listByAuther(userId);

        return R.ok().data("menuList",menuList);
    }

    @GetMapping("list")
    @ApiOperation("构建树形菜单（包含所有菜单组件）")
    public R listMenu(){
        List<MenuForAclVo> menuList = menuService.treeMenu();

        return R.ok().data("menuList",menuList);
    }

    @GetMapping("getTreeSelected/{id}")
    @ApiOperation("根据角色ID获取 角色拥有的菜单ID列表")
    public R getTreeSelected(@ApiParam("角色ID") @PathVariable("id") String roleId){
        List<String> menuIdList = menuService.listIdsByRoleId(roleId);

        return R.ok().data("menuIdList",menuIdList);
    }
}

