package com.fly.user.common.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 外部接口调用类封装
 *
 * @author free loop
 * @version 1.0
 * @since 2020/6/18 11:11
 */
@Slf4j
@Component
public class RestCall {


    /**
     * get请求封装类
     *
     * @param url     请求地址
     * @param headers 请求头
     * @return result
     * @throws IOException 调用异常
     */
    public static String get(String url, Map<String, String> headers) throws IOException {

        long start = System.currentTimeMillis();
        try {
            String result = OkUtil.callGet(url, headers);
            log.info("外部请求 - [GET] 耗时[{}] - url:{} - headers:{} - result:{}", System.currentTimeMillis() - start, url, headers, result);
            return result;
        } catch (IOException e) {
            log.info("外部请求 - [GET] 耗时[{}] - url:{} - headers:{} - 异常:{}", System.currentTimeMillis() - start, url, headers, e.getMessage());
            throw e;
        }
    }

    /**
     * post json 请求封装类
     *
     * @param url      请求地址
     * @param headers  请求头
     * @param jsonBody 请求体json字符串
     * @return result
     * @throws IOException 调用异常
     */
    public static String postJson(String url, Map<String, String> headers, String jsonBody) throws IOException {

        long start = System.currentTimeMillis();
        try {
            String result = OkUtil.callPostByJson(url, headers, jsonBody);
            log.info("外部请求 - [POST] 耗时[{}] - url:{} - headers:{} - params:{} - result:{}",
                    System.currentTimeMillis() - start, url, headers, jsonBody, result);
            return result;
        } catch (IOException e) {
            log.info("外部请求 - [POST] 耗时[{}] - url:{} - headers:{} - params:{} - 异常:{}",
                    System.currentTimeMillis() - start, url, headers, jsonBody, e.getMessage());
            throw e;
        }
    }

    /**
     * post json 请求封装类
     *
     * @param url     请求地址
     * @param headers 请求头
     * @param form    请求表单参数
     * @return result
     * @throws IOException 调用异常
     */
    public static String postForm(String url, Map<String, String> headers, Map<String, String> form) throws IOException {

        long start = System.currentTimeMillis();
        try {
            String result = OkUtil.callPostByForm(url, headers, form);
            log.info("外部请求 - [POST] 耗时[{}] - url:{} - headers:{} - params:{} - result:{}", System.currentTimeMillis() - start, url, headers, form, result);
            return result;
        } catch (IOException e) {
            log.info("外部请求 - [POST] 耗时[{}] - url:{} - headers:{} - params:{} - 异常:{}", System.currentTimeMillis() - start, url, headers, form, e.getMessage());
            throw e;
        }
    }

}
