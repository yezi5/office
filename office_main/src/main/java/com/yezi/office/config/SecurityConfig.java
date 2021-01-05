package com.yezi.office.config;

import cn.hutool.crypto.SecureUtil;
import com.yezi.office.acl.filter.TokenFilter;
import com.yezi.office.acl.filter.UsernamePasswordFilter;
import com.yezi.office.acl.handel.UserAuthenticationEntryPoint;
import com.yezi.office.acl.handel.UserAuthenticationFailureHandler;
import com.yezi.office.acl.handel.UserAuthenticationSuccessHandler;
import com.yezi.office.acl.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.config
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2021/1/4 星期一 10:44
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 自定义未登录处理器
     */
    @Autowired
    private UserAuthenticationEntryPoint userAuthenticationEntryPoint;
    /**
     * 自定义认证成功处理器
     */
    @Autowired
    private UserAuthenticationSuccessHandler successHandler;
    /**
     * 自定义认证失败处理器
     */
    @Autowired
    private UserAuthenticationFailureHandler failureHandler;
    @Autowired
    private TokenFilter tokenFilter;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().configurationSource(corsConfigurationSource());

        http.formLogin()
                .loginProcessingUrl("/office/user/login").permitAll()
                .successHandler(successHandler)
                .failureHandler(failureHandler);

        http.authorizeRequests()
                .antMatchers(
                        "/userlogin",
                        "/userlogout",
                        "/userjwt",
                        "/v2/api-docs",
                        "/swagger-resources/configuration/ui",
                        "/swagger-resources",
                        "/swagger-resources/configuration/security",
                        "/swagger-ui.html",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/webjars/**",
                        "/import/test",
                        "**/favicon.ico").permitAll()
                .antMatchers(
                        "/office/user/login",
                        "/office/user/logout").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                //指定自定义的未登录认证处理类
                .exceptionHandling()
                .authenticationEntryPoint(userAuthenticationEntryPoint)
                .and()
                .addFilterBefore(tokenFilter,UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(usernamePasswordAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //指定自定义UserDetailsService实现类
        auth.userDetailsService(userDetailsService);
    }

    //跨域配置
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");    //同源配置，*表示任何请求都视为同源，若需指定ip和端口可以改为如“localhost：8080”，多个以“，”分隔；
        corsConfiguration.addAllowedHeader("*");//header，允许哪些header，本案中使用的是token，此处可将*替换为token；
        corsConfiguration.addAllowedMethod("*");    //允许的请求方法，PSOT、GET等
        ((UrlBasedCorsConfigurationSource) source).registerCorsConfiguration("/**", corsConfiguration); //配置允许跨域访问的url
        return source;
    }


    @Bean
    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter(){
        UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = null;
        try {
            usernamePasswordAuthenticationFilter = new UsernamePasswordFilter(authenticationManager());
        } catch (Exception e) {
            e.printStackTrace();
        }
        usernamePasswordAuthenticationFilter.setFilterProcessesUrl("/office/user/login");
        usernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
        usernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(failureHandler);
        return usernamePasswordAuthenticationFilter;
    }

    /**
     * 注入自定义的密码加密类
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return SecureUtil.md5(rawPassword.toString());
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(SecureUtil.md5(rawPassword.toString()));
            }
        };
    }
}
