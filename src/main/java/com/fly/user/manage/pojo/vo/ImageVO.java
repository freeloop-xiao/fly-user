package com.fly.user.manage.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/8/6 11:17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "图形验证码VO", description = "图形验证码VO")
public class ImageVO {

    @ApiModelProperty(value = "图形验证码序号")
    private String serNo;

    @ApiModelProperty(value = "图形验证码")
    private String image;


    private String code;


    public ImageVO(String serNo, String image) {
        this.serNo = serNo;
        this.image = image;
    }
}
