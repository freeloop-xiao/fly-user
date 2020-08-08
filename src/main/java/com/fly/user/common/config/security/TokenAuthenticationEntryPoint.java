package com.fly.user.common.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fly.user.common.enums.ResponseCode;
import com.fly.user.common.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义认证拦截器
 *
 * @author free loop
 * @version 1.0
 * @since 2020/3/12 16:16
 */
@Slf4j
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        writeTokenInvalidJSONData(response);
    }

    private void writeTokenInvalidJSONData(HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), new R<>(ResponseCode.TOKEN_INVALID.getCode(), null, ResponseCode.TOKEN_INVALID.getMsg()));
    }
}
