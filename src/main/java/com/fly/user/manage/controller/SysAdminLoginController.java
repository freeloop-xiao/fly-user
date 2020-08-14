package com.fly.user.manage.controller;

import com.fly.user.common.controller.BaseController;
import com.fly.user.common.vo.R;
import com.fly.user.common.vo.TokenVO;
import com.fly.user.manage.pojo.dto.SysAdminLoginRequest;
import com.fly.user.manage.pojo.vo.ImageVO;
import com.fly.user.manage.service.SysAdminLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 系统管理员登陆 前端控制器
 * </p>
 *
 * @author free loop
 * @since 2020-07-29
 */
@Api(tags = "系统管理员登陆接口(web)")
@RestController
@RequestMapping("/sys-auth")
public class SysAdminLoginController extends BaseController {

    @Autowired
    private SysAdminLoginService sysAdminLoginService;

    /**
     * 获取图形验证码
     *
     * @return picture
     */
    @ApiOperation(value = "获取图形验证码", notes = "获取图形验证码")
    @GetMapping("/captcha")
    public R<ImageVO> captcha() {
        return new R<>(sysAdminLoginService.captcha());
    }

    /**
     * 管理员登陆
     *
     * @param request 登陆信息
     * @return token
     */
    @ApiOperation(value = "管理员登陆", notes = "管理员登陆")
    @PostMapping("/login")
    public R<TokenVO> login(@Valid @RequestBody SysAdminLoginRequest request) {
        return new R<>(sysAdminLoginService.login(request));
    }

    /**
     * 管理员token刷新
     *
     * @param refreshToken refreshToken
     * @return token
     */
    @ApiOperation(value = "管理员刷新token", notes = "管理员刷新token")
    @PostMapping("/refresh")
    public R<TokenVO> refresh(@RequestParam String refreshToken) {
        // 刷新token
        return new R<>(sysAdminLoginService.refresh(refreshToken));
    }

}
