package com.yezi.office.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 日志记录表
 * </p>
 *
 * @author 叶子
 * @since 2021-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("office_log")
@ApiModel(value="Log对象", description="日志记录表")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日志记录主键")
      @TableId(value = "log_id", type = IdType.ID_WORKER_STR)
    private String logId;

    @ApiModelProperty(value = "访问用户")
    private String username;

    @ApiModelProperty(value = "访问IP")
    private String ip;

    @ApiModelProperty(value = "访问资源URL")
    private String url;

    @ApiModelProperty(value = "访问时间")
    private Date visitTime;

    @ApiModelProperty(value = "执行时间")
    private Long executionTime;

    @ApiModelProperty(value = "访问方法")
    private String method;


}
