package com.fly.user.manage.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 添加角色请求类
 * </p>
 *
 * @author free loop
 * @since 2020-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysRole添加请求", description="SysRole添加请求")
public class SysRoleSaveRequest {

    @NotEmpty(message = "角色名称不能为空")
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @NotEmpty(message = "角色code不能为空")
    @ApiModelProperty(value = "角色code")
    private String roleCode;

    @NotEmpty(message = "角色描叙不能为空")
    @ApiModelProperty(value = "角色描叙")
    private String roleDesc;


}
