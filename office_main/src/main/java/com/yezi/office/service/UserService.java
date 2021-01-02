package com.yezi.office.service;

import com.yezi.office.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yezi.office.pojo.para.Query;
import com.yezi.office.pojo.vo.UserVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 叶子
 * @since 2020-12-29
 */
public interface UserService extends IService<User> {

    /**
     * 获取用户列表
     *
     * 条件查询+分页查询
     * @return
     */
    Map<String,Object> pageQuery(Query query);

    /**
     * 根据用户ID获取用户信息
     * @param userId
     * @return
     */
    Map<String,Object> getUserVoById(String userId);

    /**
     * 根据用户名 模糊查询 相关用户ID
     * @param username
     * @return
     */
    List<String> listByUserName(String username);
}
