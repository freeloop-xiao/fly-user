package com.fly.user.manage.pojo.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/7/30 21:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="菜单添加请求", description="菜单添加请求")
public class SysMenuSaveRequest {

    @NotEmpty(message = "菜单名称不能为空")
    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "图标")
    private String icon;

    @NotEmpty(message = "前端path不能为空")
    @ApiModelProperty(value = "前端path")
    private String path;

    @ApiModelProperty(value = "请求链接")
    private String url;

    @NotNull(message = "父菜单id不能为空")
    @ApiModelProperty(value = "父菜单ID")
    private Integer parentId;

    @ApiModelProperty(value = "排序值")
    private Integer sort = 0;

}
