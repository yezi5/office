package com.yezi.office.acl.service;

import com.yezi.office.acl.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yezi.office.acl.pojo.vo.MenuVo;

import java.util.List;

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
}
