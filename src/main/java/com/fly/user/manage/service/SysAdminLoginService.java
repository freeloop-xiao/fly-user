package com.fly.user.manage.service;

import com.fly.user.common.vo.TokenVO;
import com.fly.user.manage.pojo.dto.SysAdminLoginRequest;
import com.fly.user.manage.pojo.vo.ImageVO;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/8/5 20:44
 */
public interface SysAdminLoginService {

    /**
     * 生成
     * @return imageVO
     */
    ImageVO captcha();


    /**
     * 系统管理员登陆
     * @param request request
     * @return  token
     */
    TokenVO login(SysAdminLoginRequest request);

}
