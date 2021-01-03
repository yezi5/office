package com.yezi.office.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 公告表
 * </p>
 *
 * @author 叶子
 * @since 2021-01-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("office_bulletin")
@ApiModel(value="Bulletin对象", description="公告表")
public class Bulletin implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公告主键")
      @TableId(value = "bulletin_id", type = IdType.ID_WORKER_STR)
    private String bulletinId;

    @ApiModelProperty(value = "公告标题")
    private String bulletinTitle;

    @ApiModelProperty(value = "公告内容")
    private String bulletinContext;

    @ApiModelProperty(value = "是否生效（0代表不生效，1代表生效）")
    @TableField(fill = FieldFill.INSERT)
    private Integer bulletinIsactive;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @ApiModelProperty(value = "创建用户")
    private String createUserId;

    @ApiModelProperty(value = "修改用户")
    private String updateUserId;

}
