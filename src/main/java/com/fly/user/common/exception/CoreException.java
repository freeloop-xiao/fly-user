package com.fly.user.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiao kun
 * @version 1.0
 * @since 2019-10-28 14:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CoreException extends RuntimeException {

    private String code;

    private Object data;


    public static void throwException(String message) {
        throw new CoreException(message);
    }

    public CoreException() {
    }

    public CoreException(String message) {
        super(message);
    }

    public CoreException(String code, String message) {
        super(message);
        this.code = code;
    }

    public CoreException(String message, Throwable cause) {
        super(message, cause);
    }
}