package com.yezi.office.pojo.para;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.pojo.para
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2020/12/30 星期三 14:24
 */
@Data
@ApiModel("封装添加用户信息")
public class UserInfo {

    @ApiModelProperty(value = "用户ID")
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

    @ApiModelProperty(value = "部门ID")
    private String userDapartmentId;

}
