package com.fly.user.common.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fly.user.common.enums.ResponseCode;
import com.fly.user.common.vo.R;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/3/12 16:16
 */
public class SecurityAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        writeTokenInvalidJSONData(httpServletResponse);
    }

    private void writeTokenInvalidJSONData(HttpServletResponse response) throws IOException {

        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), new R<>(ResponseCode.ACCESS_DENIED.getCode(), null, ResponseCode.ACCESS_DENIED.getMsg()));
    }
}
