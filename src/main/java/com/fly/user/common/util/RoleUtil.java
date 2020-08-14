package com.fly.user.common.util;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/7/29 22:53
 */
public class RoleUtil {

    /**
     * 超级管理员角色
     */
    public static final String ROLE_SUPER = "hasRole('SUPER')";


    /**
     * 管理员角色
     */
    public static final String ROLE_ADMIN = "hasRole('ADMIN')";

    /**
     * 有任何用户角色
     */
    public static final String ROLE_SUPER_OR_ADMIN = "hasAnyRole('SUPER','ADMIN')";

    /**
     * 应用角色
     */
    public static final String ROLE_APP_USER = "hasRole('APP')";
}
