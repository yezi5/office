package com.yezi.office.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yezi.office.acl.pojo.UserRole;
import com.yezi.office.acl.pojo.vo.MenuVo;
import com.yezi.office.acl.service.MenuService;
import com.yezi.office.acl.service.UserRoleService;
import com.yezi.office.pojo.User;
import com.yezi.office.pojo.para.UserInfo;
import com.yezi.office.pojo.para.Query;
import com.yezi.office.pojo.vo.UserVo;
import com.yezi.office.service.AddressService;
import com.yezi.office.service.ClockService;
import com.yezi.office.service.UserService;
import com.yezi.office.utils.R;
import com.yezi.office.utils.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

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
    @Autowired
    private ClockService clockService;
    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/list")
    @ApiOperation("用户查询接口（分页查询+条件查询）")
    public R userList(@ApiParam("查询参数") @RequestBody Query query){

        Map<String, Object> map = userService.pageQuery(query);

        return R.ok().data(map);
    }

    @DeleteMapping("/delete/{userId}")
    @ApiOperation("根据用户ID删除用户")
    @Transactional
    public R delete(@ApiParam("用户ID") @PathVariable("userId") String userId){
        userService.removeById(userId);
        UpdateWrapper<UserRole> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_id",userId);
        userRoleService.remove(wrapper);

        return R.ok();
    }

    @PostMapping("/add")
    @ApiOperation("添加用户信息")
    public R addUser(@ApiParam("用户详细信息") @RequestBody UserInfo userInfo){
        User user = new User();
        BeanUtils.copyProperties(userInfo,user);
        user.setPassword(SecureUtil.md5(user.getPassword()));
        userService.save(user);

        return R.ok();
    }

    @GetMapping("/get/{id}")
    @ApiOperation("根据用户ID获取用户信息")
    public R getUserById(@ApiParam("用户ID") @PathVariable("id") String userId){
        Map<String, Object> map = userService.getUserVoById(userId);

        return R.ok().data(map);
    }

    @PostMapping("/update")
    @ApiOperation("根据用户ID更新用户信息")
    @Transactional
    public R update(@ApiParam("用户详细信息") @RequestBody UserInfo userInfo){
        User user = new User();
        BeanUtils.copyProperties(userInfo,user);
        userService.updateById(user);
        return R.ok();
    }

    @GetMapping("/test")
    @ApiOperation("获取地区列表 暂时没有使用")
    public R test(){
        return R.ok().data("options",addressService.list());
    }

    @GetMapping("/signIn")
    @ApiOperation("用户签到接口")
    public R signIn(HttpServletRequest request){
        String userId = TokenUtils.getUserIdByJwtToken(request);
        int rs = clockService.signIn(userId);
        if (rs == 0){
            return R.error().message("不在规定打卡时间，打卡失败");
        }
        if (rs == 2){
            return R.error().message("已经打卡，无需再次打卡");
        }
        if (rs == 3){
            return R.error().message("打卡失败");
        }

        return R.ok();
    }

    @GetMapping("/signOut")
    @ApiOperation("用户签退接口")
    public R signOut(HttpServletRequest request){
        String userId = TokenUtils.getUserIdByJwtToken(request);
        int rs = clockService.signOut(userId);
        if (rs == 0){
            return R.error().message("不在规定签退时间，签退失败");
        }
        if (rs == 2){
            return R.error().message("已经签退，无需再次签退");
        }
        if (rs == 3){
            return R.error().message("签退失败");
        }

        return R.ok();
    }

    @PostMapping("getLoginUser")
    @ApiOperation("获取当前登录用户")
    public R getLoginUser(HttpServletRequest request){
        String userId = TokenUtils.getUserIdByJwtToken(request);

        if (StrUtil.isEmpty(userId)){
            return R.error();
        }else {
            UserVo userInfo = userService.getLoginUser(userId);
            return R.ok().data("userInfo",userInfo);
        }
    }

    // 此接口不再使用，移至 menuController
    /*@GetMapping("buildPage")
    public R buildPage(HttpServletRequest request){
        String userId = TokenUtils.getUserIdByJwtToken(request);

        List<MenuVo> menuList = menuService.listByAuther(userId);

        return R.ok().data("menuList",menuList);
    }*/
}

