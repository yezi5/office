package com.yezi.office.service;

import com.yezi.office.pojo.vo.AddressVo;

import java.util.List;

/**
 * @author 叶子
 * @Description 获取全国省市的服务类
 * @PackageName com.yezi.office.service
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2020/12/30 星期三 16:11
 */
public interface AddressService {

    List<AddressVo> list();
}
