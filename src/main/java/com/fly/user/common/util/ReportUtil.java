package com.fly.user.common.util;


import com.fly.user.common.exception.CoreException;

/**
 * @author xiaokun
 * @version 1.0
 * @since 2020-03-10 16:12
 * 异常抛出类
 */

public class ReportUtil {

    /**
     * 异常抛出方法
     *
     * @param code 响应码
     * @param msg  说明
     */
    public static void throwEx(String code, String msg) {
        throw new CoreException(code, msg);
    }

    /**
     * 异常抛出方法
     *
     * @param msg 说明
     */
    public static void throwEx(String msg) throws CoreException {
        throw new CoreException(msg);
    }
}
