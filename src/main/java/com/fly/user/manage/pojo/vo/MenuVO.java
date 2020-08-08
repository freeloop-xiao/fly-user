package com.fly.user.manage.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="菜单树节点", description="菜单树节点")
public class MenuVO {

    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "前端URL")
    private String path;

    @ApiModelProperty(value = "请求链接")
    private String url;

    @ApiModelProperty(value = "父菜单ID")
    private Long parentId;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "排序值")
    private Integer sort;

    @ApiModelProperty(value = "孩子节点")
    private List<MenuVO> children;

}
