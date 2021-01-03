package com.yezi.office.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yezi.office.pojo.Address;
import com.yezi.office.pojo.vo.AddressChildren;
import com.yezi.office.pojo.vo.AddressVo;
import com.yezi.office.service.AddressService;
import com.yezi.office.utils.RequestUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.service.impl
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2020/12/30 星期三 16:19
 */
@Component
public class AddressServiceImpl implements AddressService {
    private static final String URL = "http://datavmap-public.oss-cn-hangzhou.aliyuncs.com/areas/csv/340000_city.json";

    @Override
    public List<AddressVo> list() {
        List<AddressVo> rs = new ArrayList<>();

        String json = RequestUtils.getAddress(URL);
        Map<String,Object> map = JSONObject.parseObject(json, Map.class);
        List<Address> rows = JSONObject.parseArray(map.get("rows").toString(), Address.class);

        for (Address address : rows) {
            AddressChildren children = new AddressChildren();
            children.setValue(address.getName());
            children.setLabel(address.getName());

            AddressVo addressVo = new AddressVo();
            addressVo.setValue(address.getParent());
            addressVo.setLabel(address.getParent());
            addressVo.setChildren(children);
            rs.add(addressVo);
        }

        return rs;
    }
}
