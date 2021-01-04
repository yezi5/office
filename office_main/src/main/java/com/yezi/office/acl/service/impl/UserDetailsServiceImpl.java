package com.yezi.office.acl.service.impl;

import com.yezi.office.acl.pojo.SecurityUser;
import com.yezi.office.acl.service.UserRoleService;
import com.yezi.office.pojo.User;
import com.yezi.office.service.UserService;
import com.yezi.office.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.acl.service.impl
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2021/1/4 星期一 10:20
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.loadByUserName(username);
        if (user == null){
            throw new UsernameNotFoundException("没有该用户信息");
        }
        List<String> roleNameList = userRoleService.listRoleIdByUserId(user.getUserId());

        SecurityUser securityUser = new SecurityUser();
        securityUser.setCurrentUserInfo(user);
        securityUser.setPermissionValueList(roleNameList);

        return securityUser;
    }

    public UserDetails getUserByToken(String token){
        String username = TokenUtils.getUserNameByJwtToken(token);
        UserDetails userDetails = loadUserByUsername(username);

        return userDetails;
    }
}
