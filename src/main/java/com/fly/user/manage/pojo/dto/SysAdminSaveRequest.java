package com.fly.user.manage.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fly.user.common.util.FormatParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/8/5 15:42
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "系统管理员添加请求", description = "系统管理员添加请求")
public class SysAdminSaveRequest {

    @ApiModelProperty(value = "账号")
    private String account;

    @Pattern(regexp = FormatParam.MOBILE_PATTERN, message = "手机号格式有误")
    @NotBlank(message = "用户手机号不能为空")
    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱地址")
    private String email;

    @NotEmpty(message = "密码不能为空")
    @Pattern(regexp = "[0-9a-zA-Z]{6,30}",
            message = "密码需要同时包含数字，字母，特殊符号")
    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "头像url")
    private String avatar;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "性别 0:女  1:男  2:保密")
    private Integer sex;

    @ApiModelProperty(value = "出身日期yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate birthday;

    @ApiModelProperty(value = "系统标识")
    private String appId;

}
