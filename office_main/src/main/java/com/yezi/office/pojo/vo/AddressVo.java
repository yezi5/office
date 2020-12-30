package com.yezi.office.pojo.vo;

import com.yezi.office.pojo.Address;
import com.yezi.office.pojo.para.AddUser;
import lombok.Data;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.pojo.vo
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2020/12/30 星期三 16:19
 */
@Data
public class AddressVo {

    private String value;
    private String label;
    private AddressChildren children;
}
