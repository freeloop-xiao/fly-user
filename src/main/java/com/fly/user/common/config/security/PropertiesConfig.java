package com.fly.user.common.config.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author free loop
 * @version 1.0
 * @since 2019-09-28 14:05
 */
@Data
@Component
public class PropertiesConfig {

    /**
     *  accessToken超时时间(单位：天)
     */
    @Value("${token.expire.access}")
    private Integer accessTokenExpire;

    /**
     * refreshToken超时时间(单位：天)
     */
    @Value("${token.expire.refresh}")
    private Integer refreshTokenExpire;



}