package com.yezi.office.pojo.para;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.pojo.para
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2020/12/30 星期三 10:01
 */
@Data
@ApiModel("查询参数")
public class Query {
    @ApiModelProperty("部门名")
    private String departName;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("当前页")
    private Integer pageIndex;
    @ApiModelProperty("每页数据量")
    private Integer pageSize;
}
