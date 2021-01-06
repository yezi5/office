package com.yezi.office.pojo.para;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.pojo.para
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2021/1/3 星期日 11:28
 */
@Data
@ApiModel("封装查询参数")
public class BulletinQuery {
    @ApiModelProperty("公告标题")
    private String bulletinTitle;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("开始时间 在 开始时间-->结束时间 这段时间内创建的公告")
    private Date startTime;
    @ApiModelProperty("结束时间 在 开始时间-->结束时间 这段时间内创建的公告")
    private Date endTime;
    @ApiModelProperty("公告状态（是否激活） 1 激活 0 未激活")
    private Integer bulletinIsactive;
    @ApiModelProperty("当前页")
    private Integer pageIndex;
    @ApiModelProperty("每页数据量")
    private Integer pageSize;
}
