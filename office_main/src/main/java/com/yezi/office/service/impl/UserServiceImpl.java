package com.yezi.office.service.impl;

import com.yezi.office.pojo.User;
import com.yezi.office.mapper.UserMapper;
import com.yezi.office.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 叶子
 * @since 2020-12-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
