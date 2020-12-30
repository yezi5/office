package com.yezi.office.controller;


import com.yezi.office.pojo.User;
import com.yezi.office.pojo.para.AddUser;
import com.yezi.office.pojo.para.Query;
import com.yezi.office.pojo.vo.UserVo;
import com.yezi.office.service.AddressService;
import com.yezi.office.service.UserService;
import com.yezi.office.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 叶子
 * @since 2020-12-29
 */
@RestController
@RequestMapping("/office/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;

    @PostMapping("/list")
    public R userList(@RequestBody Query query){

        Map<String, Object> map = userService.pageQuery(query);

        return R.ok().data(map);
    }

    @DeleteMapping("/delete/{userId}")
    public R delete(@PathVariable("userId") String userId){
        userService.removeById(userId);
        return R.ok();
    }

    @PostMapping("/add")
    public R addUser(@RequestBody AddUser userInfo){
        User user = new User();
        BeanUtils.copyProperties(userInfo,user);
        userService.save(user);

        return R.ok();
    }

    @GetMapping("/test")
    public R test(){
        return R.ok().data("options",addressService.list());
    }
}

