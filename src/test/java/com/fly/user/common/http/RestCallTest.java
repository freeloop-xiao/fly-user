package com.fly.user.common.http;

import org.junit.jupiter.api.Test;


/**
 * @author free loop
 * @version 1.0
 * @since 2020/8/15 10:54
 */
class RestCallTest {

    @Test
    void get() throws Exception{
        for (int i = 0; i < 100; i++) {
            RestCall.get("https://www.baidu.com",null);
        }
    }

    @Test
    void postJson() throws Exception{
        RestCall.postJson("https://www.baidu.com",null,"{}");

    }

    @Test
    void postForm() throws Exception{
        RestCall.postForm("https://www.baidu.com",null,null);
    }
}