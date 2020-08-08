package com.fly.user.common.config;


import com.fly.user.common.exception.CoreException;
import com.fly.user.common.vo.R;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕获
 *
 * @author : xiao kun
 * @since : 2019-09-23
 */
@RestControllerAdvice
public class CommonExceptionHandler {




    /**
     * 全局异常捕获
     *
     * @param e exception
     * @return
     */
    @ExceptionHandler(value = CoreException.class)
    public R<?> coreExceptionHandler(CoreException e) {
        R<?> result = R.newFailure(e.getMessage());
        String code = e.getCode();
        if (!StringUtils.isEmpty(e.getCode())) {
            result.setCode(code);
        }
        return result;
    }


    /**
     * 请求参数验证异常捕获
     *
     * @param e 异常
     * @return 异常署吗
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R<?> validExceptionHandler(MethodArgumentNotValidException e) {
        return R.newFailure(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 全局异常捕获
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    public R<?> exceptionHandler(RuntimeException e) {
        e.printStackTrace();
        return R.newFailure();
    }
}