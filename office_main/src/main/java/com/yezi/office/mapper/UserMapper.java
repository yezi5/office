package com.yezi.office.mapper;

import com.yezi.office.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yezi.office.pojo.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 叶子
 * @since 2020-12-29
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
