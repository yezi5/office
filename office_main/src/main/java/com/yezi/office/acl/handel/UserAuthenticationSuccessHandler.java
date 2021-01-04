package com.yezi.office.acl.handel;

import com.yezi.office.acl.pojo.SecurityUser;
import com.yezi.office.pojo.User;
import com.yezi.office.service.UserService;
import com.yezi.office.utils.R;
import com.yezi.office.utils.ResponseUtil;
import com.yezi.office.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 叶子
 * @Description 认证成功处理器
 * @PackageName com.yezi.office.acl.handel
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2021/1/4 星期一 11:14
 */
@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = securityUser.getCurrentUserInfo();
        String token = TokenUtils.getJwtToken(user.getUserId(), user.getUsername());
        user.setToken(token);
        userService.refreshToken(user.getUserId(),user.getToken());
        securityUser.setCurrentUserInfo(user);
        ResponseUtil.out(httpServletResponse, R.ok().message("登陆成功").data("token",token));
    }
}
