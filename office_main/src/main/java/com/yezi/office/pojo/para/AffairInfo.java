package com.yezi.office.pojo.para;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.pojo.para
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2021/1/5 星期二 20:40
 */
@Data
@ApiModel("封装事务详细信息")
public class AffairInfo {

    @ApiModelProperty("事务类型")
    private String affairType;
    @ApiModelProperty("事务开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date affairStartTime;
    @ApiModelProperty("事务结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date affairEndTime;
    @ApiModelProperty("事务申请原因")
    private String affairReason;
    @ApiModelProperty("事务地址")
    private String affairAddress;
    @ApiModelProperty("申请金额")
    private String affairMoney;
}
