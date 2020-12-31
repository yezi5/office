package com.yezi.office.controller;


import com.yezi.office.pojo.User;
import com.yezi.office.pojo.para.UserInfo;
import com.yezi.office.pojo.para.Query;
import com.yezi.office.service.AddressService;
import com.yezi.office.service.UserService;
import com.yezi.office.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 叶子
 * @since 2020-12-29
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/office/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;

    @PostMapping("/list")
    @ApiOperation("用户查询接口（分页查询+条件查询）")
    public R userList(@RequestBody Query query){

        Map<String, Object> map = userService.pageQuery(query);

        return R.ok().data(map);
    }

    @DeleteMapping("/delete/{userId}")
    @ApiOperation("根据用户ID删除用户")
    public R delete(@ApiParam("用户ID") @PathVariable("userId") String userId){
        userService.removeById(userId);
        return R.ok();
    }

    @PostMapping("/add")
    @ApiOperation("添加用户信息")
    public R addUser(@RequestBody UserInfo userInfo){
        User user = new User();
        BeanUtils.copyProperties(userInfo,user);
        userService.save(user);

        return R.ok();
    }

    @GetMapping("/get/{id}")
    public R getUserById(@PathVariable("id") String userId){
        Map<String, Object> map = userService.getUserVoById(userId);

        return R.ok().data(map);
    }

    @PostMapping("/update")
    @Transactional
    public R update(@RequestBody UserInfo userInfo){
        User user = new User();
        BeanUtils.copyProperties(userInfo,user);
        userService.updateById(user);
        return R.ok();
    }

    @GetMapping("/test")
    public R test(){
        return R.ok().data("options",addressService.list());
    }
}

