package com.yezi.office.pojo.para;

import lombok.Data;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.pojo.para
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2020/12/30 星期三 10:01
 */
@Data
public class Query {
    private String departName;
    private String username;
    private Integer pageIndex;
    private Integer pageSize;
}
