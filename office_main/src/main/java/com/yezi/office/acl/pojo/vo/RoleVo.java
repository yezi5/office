package com.yezi.office.acl.pojo.vo;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.acl.pojo.vo
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2021/1/6 星期三 9:51
 */
@Data
public class RoleVo {

    @ApiModelProperty(value = "主键")
    private String roleId;
    @ApiModelProperty(value = "角色代码")
    private String roleCode;
    @ApiModelProperty(value = "角色名")
    private String roleName;
    @ApiModelProperty(value = "创建时间")
    private String create;

    public void setCreate(Date create) {
        this.create = DateUtil.format(create,"yyyy-MM-dd HH:mm:ss");
    }
}
