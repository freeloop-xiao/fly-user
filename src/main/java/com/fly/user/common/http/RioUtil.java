package com.fly.user.common.http;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

import java.util.HashMap;
import java.util.Map;

/**
 * 里约网关请求工具类
 *
 * @author free loop
 * @version 1.0
 * @since 2020/6/17 11:44
 */
public class RioUtil {

    public static final String PASSID_HEADER = "x-tif-paasid";

    public static final String TIMESTAMP = "x-tif-timestamp";

    public static final String SIGN = "x-tif-signature";

    public static final String NONCE = "x-tif-nonce";


    /**
     * 创建里约网关请求头
     *
     * @param passId rio - passId
     * @param token  rio - token
     * @return builder
     */
    private static Map<String, String> createRioHeaders(String passId, String token) {
        String nonce = IdUtil.fastSimpleUUID().substring(0, 16);
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        Digester sha256 = new Digester(DigestAlgorithm.SHA256);
        String sign = sha256.digestHex(timestamp + token + nonce + timestamp).toUpperCase();
        Map<String, String> headers = new HashMap<>(8);
        headers.put(PASSID_HEADER, passId);
        headers.put(TIMESTAMP, timestamp);
        headers.put(SIGN, sign);
        headers.put(NONCE, nonce);
        return headers;
    }

}