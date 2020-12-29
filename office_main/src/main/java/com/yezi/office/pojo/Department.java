package com.yezi.office.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("office_department")
@ApiModel(value="Department对象", description="")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门主键")
      @TableId(value = "depart_id", type = IdType.ID_WORKER_STR)
    private String departId;

    @ApiModelProperty(value = "部门名称")
    private String departName;

    @ApiModelProperty(value = "部门描述")
    private String departDescription;

    @ApiModelProperty(value = "部门人数")
    private Integer departUserCount;

    @ApiModelProperty(value = "部门电话")
    private String departPhone;


}
