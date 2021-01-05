package com.yezi.office.acl.filter;

import com.yezi.office.acl.pojo.SecurityUser;
import com.yezi.office.acl.service.impl.UserDetailsServiceImpl;
import com.yezi.office.pojo.User;
import com.yezi.office.utils.MultiReadHttpServletRequest;
import com.yezi.office.utils.MultiReadHttpServletResponse;
import com.yezi.office.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.acl.filter
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2021/1/4 星期一 11:21
 */
@Component
public class TokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsServiceImpl service;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        MultiReadHttpServletRequest wrappedRequest = new MultiReadHttpServletRequest(httpServletRequest);
        MultiReadHttpServletResponse wrappedResponse = new MultiReadHttpServletResponse(httpServletResponse);

        String token = wrappedRequest.getHeader(TokenUtils.TOKEN_NAME);
        System.out.println(token);
        if (!"undefined".equals(token)){
            if (!TokenUtils.checkToken(token)){
                throw new AccessDeniedException("token已过期，请重新登录！");
            }
            SecurityUser securityUser = (SecurityUser) service.getUserByToken(token);
            if (securityUser == null || securityUser.getCurrentUserInfo() == null){
                throw new AccessDeniedException("token失效，请重新登录！");
            }

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(securityUser, null, securityUser.getAuthorities());
            // 全局注入角色权限信息和登录用户基本信息
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(wrappedRequest, wrappedResponse);
    }
}
