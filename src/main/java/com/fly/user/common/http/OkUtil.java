package com.fly.user.common.http;

import com.fly.user.common.http.ok.OkClientFactory;
import okhttp3.*;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/6/17 11:44
 */
public class OkUtil {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    /**
     * http get 请求
     *
     * @param url     url
     * @param headers 请求头
     * @return String
     * @throws IOException
     */
    protected static String callGet(String url, Map<String, String> headers) throws IOException {
        Request.Builder builder = builder(headers)
                .url(url)
                .get();
        OkHttpClient client = OkClientFactory.getClient(url);
        return client.newCall(builder.build()).execute().body().string();
    }


    /**
     * http post json请求里约网关
     *
     * @param url      url
     * @param headers  headers
     * @param jsonBody 请求json对象字符串
     * @return String
     * @throws IOException IOException
     */
    protected static String callPostByJson(String url, Map<String, String> headers, String jsonBody) throws IOException {

        if (StringUtils.isEmpty(jsonBody)) {
            jsonBody = "{}";
        }

        Request.Builder requestBuilder = builder(headers)
                .url(url)
                .post(RequestBody.create(JSON, jsonBody));
        OkHttpClient client = OkClientFactory.getClient(url);
        return client.newCall(requestBuilder.build()).execute().body().string();
    }


    /**
     * http post from请求里约网关
     *
     * @param url     url
     * @param headers 请求头
     * @param form    form表单数据
     * @return String
     * @throws IOException IOException
     */
    protected static String callPostByForm(String url, Map<String, String> headers, Map<String, String> form) throws IOException {

        Request.Builder requestBuilder = builder(headers).url(url);
        if (form != null && form.size() > 0) {
            FormBody.Builder formBody = new FormBody.Builder();
            for (Map.Entry<String, String> entry : form.entrySet()) {
                formBody.add(entry.getKey(), entry.getValue());
            }
            requestBuilder.post(formBody.build());
        }

        OkHttpClient client = OkClientFactory.getClient(url);

        return client.newCall(requestBuilder.build()).execute().body().string();
    }


    /**
     * 创建请求builder
     *
     * @param headers 请求头
     * @return builder
     */
    protected static Request.Builder builder(Map<String, String> headers) {

        Request.Builder requestBuilder = new Request.Builder();
        if (headers == null){
            return requestBuilder;
        }
        // requestBuilder添加非rio其他请求头
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            requestBuilder.addHeader(entry.getKey(), entry.getValue());
        }
        return requestBuilder;
    }

}
