package com.yezi.office.acl.pojo.para;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.acl.pojo.para
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2021/1/6 星期三 15:03
 */
@Data
@ApiModel("封装角色信息 + 角色拥有的菜单权限")
public class RoleInfo {

    @ApiModelProperty(value = "主键")
    private String roleId;

    @ApiModelProperty(value = "角色代码")
    private String roleCode;

    @ApiModelProperty(value = "角色名")
    private String roleName;

    @ApiModelProperty(value = "菜单ID列表")
    private List<String> menuIdList;
}
