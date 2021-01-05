package com.yezi.office.acl.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.acl.pojo.vo
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2021/1/5 星期二 16:42
 */
@Data
public class MenuVo {

    @ApiModelProperty(value = "图标class属性")
    private String icon;

    @ApiModelProperty(value = "路由")
    private String index;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "子组件")
    private List<MenuVo> subs;
}
