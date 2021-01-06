package com.yezi.office.acl.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.acl.pojo.vo
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2021/1/6 星期三 9:05
 */
@Data
public class MenuForAclVo {

    @ApiModelProperty(value = "主键")
    private String menuId;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "子组件")
    private List<MenuForAclVo> subs;
}
