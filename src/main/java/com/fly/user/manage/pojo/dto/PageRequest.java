package com.fly.user.manage.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/8/5 11:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="分页请求公共类", description="分页请求公共类")
public class PageRequest {

    @ApiModelProperty(value = "页码")
    private Long page = 1L;

    @ApiModelProperty(value = "页记录条数")
    private Long limit = 10L;

    @ApiModelProperty(value = "排序字段(降序)")
    private String orderDescBy;

    @ApiModelProperty(value = "排序字段(升序)")
    private String orderAscBy;
}
