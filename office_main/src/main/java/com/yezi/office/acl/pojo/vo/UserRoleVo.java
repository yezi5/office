package com.yezi.office.acl.pojo.vo;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.acl.pojo.vo
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2021/1/6 星期三 11:15
 */
@Data
public class UserRoleVo {

    @ApiModelProperty(value = "用户主键")
    private String userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "部门名")
    private String departName;

    @ApiModelProperty(value = "创建时间")
    private String gmtCreate;

    @ApiModelProperty(value = "角色ID列表")
    private List<String> roleIdList;

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = DateUtil.format(gmtCreate,"yyyy-MM-dd HH:mm:ss");
    }
}
