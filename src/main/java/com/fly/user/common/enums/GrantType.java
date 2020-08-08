package com.fly.user.common.enums;

import lombok.Getter;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/3/12 12:52
 */
@Getter
public enum  GrantType {

    // 表单授权登录
    FORM("form","表单授权登录");

    private final String type;

    private final String desc;

    GrantType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }}
