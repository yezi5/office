package com.yezi.office.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 叶子
 * @since 2020-12-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("office_clock")
@ApiModel(value="Clock对象", description="")
public class Clock implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "打卡记录主键")
      @TableId(value = "clock_id", type = IdType.ID_WORKER_STR)
    private String clockId;

    @ApiModelProperty(value = "用户主键")
    private String clockUserId;

    @ApiModelProperty(value = "打卡时间")
    private Date clockTime;

    @ApiModelProperty(value = "下班时记录一天的工作内容")
    private String clockWork;


}
