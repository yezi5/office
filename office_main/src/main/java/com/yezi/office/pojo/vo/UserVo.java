package com.yezi.office.pojo.vo;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.pojo.vo
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2020/12/29 星期二 20:22
 */
@Data
public class UserVo {

    @ApiModelProperty(value = "用户主键")
    private String userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "住址")
    private String address;

    @ApiModelProperty(value = "QQ")
    private String qq;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "部门名")
    private String departName;

    @ApiModelProperty(value = "创建时间")
    private String create;

    public void setCreate(Date create) {
        this.create = DateUtil.format(create,"yyyy-MM-dd HH:mm:ss");
    }
}
