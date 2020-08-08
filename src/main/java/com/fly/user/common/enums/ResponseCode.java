package com.fly.user.common.enums;

import lombok.Getter;

/**
 * @author xiao kun
 * @version 1.0
 * @since 2020/3/11 15:40
 */
@Getter
public enum ResponseCode {

    // 请求成功
    SUCCESS("0000", "成功！"),
    // token过期
    TOKEN_TIME_OUT("1001", "token过期！"),
    // token无效
    TOKEN_INVALID("1002", "token无效！"),
    // 无权限访问
    ACCESS_DENIED("1003","无访问权限！");

    ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 响应编码
     */
    private final String code;

    /**
     * 响应编码说明
     */
    private final String msg;
}
