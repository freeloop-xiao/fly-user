package com.fly.user.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiao kun
 * @since 2019/10/28
 */
public class BaseController {

    @Autowired
    private HttpServletRequest request;

    /**
     * 根据请求heard中的token获取用户角色
     *
     * @return 角色名
     */
    public List<String> getRole() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(x -> (x).getAuthority()).collect(Collectors.toList());
    }

    /**
     * 根据请求heard中的token获取用户ID
     *
     * @return 用户ID
     */
    public Long getUserId() {
        return (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}
