package com.fly.user.common.http.ok;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * OkClient创建工厂类
 *
 * @author free loop
 * @version 1.0
 * @since 2020/6/18 11:02
 */
public class OkClientFactory {

    private static final OkHttpClient.Builder OK_HTTPS_CLIENT_BUILDER = OkHttpsClientBuilder.buildOKHttpClient().connectTimeout(15L, TimeUnit.SECONDS);
    private static final OkHttpClient.Builder OK_HTTP_CLIENT_BUILDER = new OkHttpClient().newBuilder()
            .connectTimeout(15L, TimeUnit.SECONDS).readTimeout(10L, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS);
    public static final String HTTPS = "https";


    public static OkHttpClient getClient(String url) {
        if (isHttps(url)) {
            return OK_HTTPS_CLIENT_BUILDER.build();
        }
        return OK_HTTP_CLIENT_BUILDER.build();
    }


    /**
     * 判断资源是否是https协议
     *
     * @param url url
     * @return true/false
     */
    public static boolean isHttps(String url) {

        if (url.startsWith(HTTPS)) {
            return true;
        }
        return false;
    }
}
