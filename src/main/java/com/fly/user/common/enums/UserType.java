package com.fly.user.common.enums;

import lombok.Getter;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/3/12 11:53
 */
@Getter
public enum UserType {

    // 管理员用户类型
    ADMIN("admin","管理员"),
    // 客户端用户类型
    CLIENT("client","客户端用户");

    private final String type;

    private final String desc;

    UserType(String type, String desc){
        this.type = type;
        this.desc = desc;
    }

}
