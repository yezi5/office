package com.yezi.office.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2021-01-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("office_affair")
@ApiModel(value="Affair对象", description="")
public class Affair implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "事务主键")
      @TableId(value = "affair_id", type = IdType.ID_WORKER_STR)
    private String affairId;

    @ApiModelProperty(value = "用户主键")
    private String affairUserId;

    @ApiModelProperty(value = "事务类型，在Java代码中用常量接口标注了不同的事务类型")
    private String affairType;

    @ApiModelProperty(value = "申请原因/申请事件，事务共有字段")
    private String affairReason;

    @ApiModelProperty(value = "是否批准，1代表批准，0代表不批准。事务共有字段")
    @TableField("affair_isOk")
    private Integer affairIsok;

    @ApiModelProperty(value = "开始时间，事务共有字段")
    private Date affairStartTime;

    @ApiModelProperty(value = "结束时间，事务共有字段")
    private Date affairEndTime;

    @ApiModelProperty(value = "共计时长，事务共有字段")
    private String affairTime;

    @ApiModelProperty(value = "申请地点，事务共有字段")
    private String affairAddress;

    @ApiModelProperty(value = "申请金额（报销事务特有字段）")
    private String affairMoney;


}
