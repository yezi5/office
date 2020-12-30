package com.yezi.office.pojo;

import lombok.Data;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.pojo
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2020/12/30 星期三 16:18
 */
@Data
public class Address {
    private String adcode;
    private Integer people_count_2010;
    private Double lat;
    private Double lng;
    private String name;
    private String level;
    private String parent;
}
