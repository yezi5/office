package com.yezi.office.service;

import com.yezi.office.pojo.Bulletin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yezi.office.pojo.para.BulletinQuery;

import java.util.Map;

/**
 * <p>
 * 公告表 服务类
 * </p>
 *
 * @author 叶子
 * @since 2021-01-02
 */
public interface BulletinService extends IService<Bulletin> {

    Map<String, Object> list(BulletinQuery query);

    int approve(String bulletinId, Integer isActive);
}
