package com.fly.user.common.vo;
import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @param <T>
 * @author xiao kun
 **/
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String SUCCESS_CODE = "0000";

    public static final String FAIL_CODE = "9999";

    private String msg = "success";

    private String code = SUCCESS_CODE;

    private T data;

    public R() {
        super();
    }

    public R(T data) {
        super();
        this.data = data;
    }


    public R(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public R(String code, T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
        this.code = code;
    }

    public R(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = FAIL_CODE;
    }

    public static <T> R newSuccess() {
        return new R<T>(SUCCESS_CODE, null, "操作成功！");
    }

    public static R newSuccess(String message) {
        if (message == null) {
            message = "操作成功！";
        }
        R r = newSuccess();
        r.setMsg(message);
        return r;
    }

    public static <T>R newFailure() {
        return new R<T>(FAIL_CODE, null, "操作失败！");
    }

    public static R newFailure(String message) {
        if (message == null) {
            message = "操作失败！";
        }
        R r = newFailure();
        r.setMsg(message);
        return r;
    }


    public R toSuccess() {
        this.code = SUCCESS_CODE;
        this.msg = "操作成功！";
        return this;
    }

    public R toFailure() {
        this.code = FAIL_CODE;
        this.msg = "操作失败！";
        return this;
    }


    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }

    public R<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public R<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public R<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

    public T getData() {
        return data;
    }
}
