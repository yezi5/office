package com.yezi.office.acl.pojo.para;

import lombok.Data;

import java.util.List;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.acl.pojo.para
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2021/1/6 星期三 14:12
 */
@Data
public class UserRoleUpdateInfo {
    private String userId;
    private List<String> roleIdList;
}
