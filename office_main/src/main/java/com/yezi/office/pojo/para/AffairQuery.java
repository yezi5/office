package com.yezi.office.pojo.para;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.pojo.para
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2021/1/1 星期五 20:31
 */
@Data
@ApiModel("查询请求参数")
public class AffairQuery { 

    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("事务类型: 请假 出差 报销 会议室申请")
    private String affairType;
    @ApiModelProperty("事务开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date affairStartTime;
    @ApiModelProperty("事务结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date affairEndTime;
    @ApiModelProperty("事务状态： 1 批准 0 不批准")
    private Integer affairIsOk;
    @ApiModelProperty("当前页数")
    private Integer pageIndex;
    @ApiModelProperty("每页数据量")
    private Integer pageSize;
}
