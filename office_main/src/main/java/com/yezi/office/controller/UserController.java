package com.yezi.office.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 叶子
 * @since 2020-12-29
 */
@Api(tags = "user控制器")
@RestController
@RequestMapping("/office/user")
public class UserController {

    @GetMapping("/test")
    public String test(){
        return "Hello";
    }
}

