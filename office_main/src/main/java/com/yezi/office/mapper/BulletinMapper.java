package com.yezi.office.mapper;

import com.yezi.office.pojo.Bulletin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 公告表 Mapper 接口
 * </p>
 *
 * @author 叶子
 * @since 2021-01-02
 */
public interface BulletinMapper extends BaseMapper<Bulletin> {

    int approve(String bulletinId, Integer isActive);
}
