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
 * @since 2020/8/2 22:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="系统部门添加请求", description="系统部门添加请求")
public class SysDeptSaveRequest {

    @NotEmpty(message = "部门名称不能为空!")
    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "排序")
    private Integer orderNum = 0;

    @NotNull(message = "父部门id不能为空!")
    @ApiModelProperty(value = "父部门id")
    private Integer parentId;

}
