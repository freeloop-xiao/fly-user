package com.fly.user.manage.pojo.vo;

import com.fly.user.manage.pojo.entity.SysAdmin;
import com.fly.user.manage.pojo.entity.SysRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/8/5 17:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="系统管理员信息", description="系统管理员信息")
public class SysAdminVO {

    @ApiModelProperty(value = "系统管理员基本信息")
    private SysAdmin sysAdmin;

    @ApiModelProperty(value = "系统管理员角色列表")
    private List<SysRole> roles;

    @ApiModelProperty(value = "部门名称")
    private String dept;

    @ApiModelProperty(value = "菜单列表")
    private List<MenuVO> menus;

}
