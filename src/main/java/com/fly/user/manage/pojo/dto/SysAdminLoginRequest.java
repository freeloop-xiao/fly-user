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
@ApiModel(value="系统管理员登陆请求", description="系统管理员登陆请求")
public class SysAdminLoginRequest {

    @NotEmpty(message = "账号不能为空")
    @ApiModelProperty(value = "手机号")
    private String phone;

    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;

    @NotEmpty(message = "图形验证码为空")
    @ApiModelProperty(value = "图形验证码")
    private String captcha;

    @NotEmpty(message = "图形验证码流水号为空")
    @ApiModelProperty(value = "图形验证码流水号")
    private String captchaSerNo;

}
