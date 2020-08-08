package com.fly.user.common.enums;

import lombok.Getter;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/3/12 11:53
 */
@Getter
public enum  TokenType {

    // 访问token
    ACCESS_TYPE("access","访问token"),
    // 刷新token
    REFRESH_TYPE("refresh","刷新token");

    private final String type;

    private final String desc;

    TokenType(String type,String desc){
        this.type = type;
        this.desc = desc;
    }

}
