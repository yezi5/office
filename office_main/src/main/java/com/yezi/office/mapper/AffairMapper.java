package com.yezi.office.mapper;

import com.yezi.office.pojo.Affair;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 叶子
 * @since 2021-01-01
 */
public interface AffairMapper extends BaseMapper<Affair> {

    int approve(String affairId, Integer affairIsOk);
}
