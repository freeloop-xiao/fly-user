package com.fly.user.common.config.aspect;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/8/14 23:31
 */
@Slf4j
@Aspect
@Component
@Profile({"dev", "test"})
public class WebLogAspect {
    /**
     * 换行符
     */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * 以自定义 @WebLog 注解为切点
     */
    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void webLog() {
    }


    /**
     * 在切点之前织入
     *
     * @param joinPoint 连接点
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = authentication == null ? null : (Long) authentication.getPrincipal();

        // 获取 @WebLog 注解的描述信息
        String methodDescription = getAspectLogDescription(joinPoint);

        // 打印请求相关参数
        log.info("=================== Start ==========================================");
        // 打印请求 url
        log.info("URL           : {}", request.getRequestURL().toString());
        log.info("UserId        : {}", userId);
        // 打印描述信息
        log.info("Description   : {}", methodDescription);
        // 打印 Http method
        log.info("HTTP Method   : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method  : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP            : {}", request.getRemoteAddr());
        // 打印请求入参
        log.info("Request Args  : {}", JSON.toJSONString(joinPoint.getArgs()));
    }


    /**
     * 在切点之后织入
     *
     * @throws Throwable
     */
    @After("webLog()")
    public void doAfter() throws Throwable {
        // 接口结束后换行，方便分割查看
        log.info("=================== End ===========================================" + LINE_SEPARATOR);
    }

    /**
     * 环绕
     *
     * @param proceedingJoinPoint 连接点
     * @return result
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            Object result = proceedingJoinPoint.proceed();
            // 打印出参
            log.info("Response Args  : {}", JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            log.info("Response Args Exception : {}", e.getMessage());
            throw e;
        } finally {
            log.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        }

    }


    /**
     * 获取切面注解的描述
     *
     * @param joinPoint 切点
     * @return 描述信息
     * @throws Exception
     */
    private String getAspectLogDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazz = method.getParameterTypes();
                if (clazz.length == arguments.length) {
                    description.append(method.getAnnotation(ApiOperation.class).notes());
                    break;
                }
            }
        }
        return description.toString();
    }

}
