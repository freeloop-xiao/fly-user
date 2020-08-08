package com.fly.user.manage.pojo.vo;

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
@ApiModel(value="部门节点", description="部门节点")
public class DeptVO {

    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "排序值")
    private Integer orderNum;

    @ApiModelProperty(value = "父ID")
    private Long parentId;

    @ApiModelProperty(value = "孩子节点")
    private List<DeptVO> children;

}
