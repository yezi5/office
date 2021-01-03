package com.yezi.office.pojo.para;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.pojo.para
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2021/1/3 星期日 10:05
 */
@Data
public class BulletinRelease {

    @ApiModelProperty(value = "公告标题")
    private String bulletinTitle;

    @ApiModelProperty(value = "公告内容")
    private String bulletinContext;
}
