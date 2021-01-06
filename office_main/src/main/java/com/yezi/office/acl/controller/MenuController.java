package com.yezi.office.acl.controller;


import com.yezi.office.acl.pojo.vo.MenuForAclVo;
import com.yezi.office.acl.pojo.vo.MenuVo;
import com.yezi.office.acl.service.MenuService;
import com.yezi.office.utils.R;
import com.yezi.office.utils.TokenUtils;
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
@RestController
@RequestMapping("/office/acl/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("buildPage")
    public R buildPage(HttpServletRequest request){
        String userId = TokenUtils.getUserIdByJwtToken(request);

        List<MenuVo> menuList = menuService.listByAuther(userId);

        return R.ok().data("menuList",menuList);
    }

    @GetMapping("list")
    public R listMenu(){
        List<MenuForAclVo> menuList = menuService.treeMenu();

        return R.ok().data("menuList",menuList);
    }

    @GetMapping("getTreeSelected/{id}")
    public R getTreeSelected(@PathVariable("id") String roleId){
        List<String> menuIdList = menuService.listIdsByRoleId(roleId);

        return R.ok().data("menuIdList",menuIdList);
    }
}

