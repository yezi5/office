package com.yezi.office.pojo.vo;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.pojo.vo
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2021/1/3 星期日 11:31
 */
@Data
public class BulletinVo {

    @ApiModelProperty(value = "公告主键")
    private String bulletinId;

    @ApiModelProperty(value = "公告标题")
    private String bulletinTitle;

    @ApiModelProperty(value = "公告内容")
    private String bulletinContext;

    @ApiModelProperty(value = "是否生效（0代表不生效，1代表生效）")
    private boolean bulletinIsactive;

    @ApiModelProperty(value = "创建时间")
    private String gmtCreate;

    @ApiModelProperty(value = "修改时间")
    private String gmtModified;

    @ApiModelProperty(value = "创建用户")
    private String createUserId;

    @ApiModelProperty(value = "修改用户")
    private String updateUserId;

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = DateUtil.format(gmtCreate,"yyyy-MM-dd HH:mm:ss");
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = DateUtil.format(gmtModified,"yyyy-MM-dd HH:mm:ss");
    }
}
