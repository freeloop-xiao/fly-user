package com.fly.user.common.filter;

import com.auth0.jwt.impl.PublicClaims;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fly.user.common.dto.AuthInfo;
import com.fly.user.common.enums.ResponseCode;
import com.fly.user.common.enums.TokenType;
import com.fly.user.common.util.ReportUtil;
import com.fly.user.common.util.TokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author free loop
 * @version 1.0
 * @since 2020/3/12 13:50
 */
public class SecurityAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String authorization = request.getHeader(TokenUtil.AUTHORIZATION);

        if (!StringUtils.isEmpty(authorization) && authorization.startsWith(TokenUtil.TOKEN_SPLIT)) {
            String token = authorization.substring(TokenUtil.TOKEN_SPLIT.length());
            DecodedJWT jwt = TokenUtil.verify(token);
            // 如果请求头中有token，则进行解析，并且设置认证信息
            if (jwt == null) {
                ReportUtil.throwEx(ResponseCode.TOKEN_INVALID.getCode(), ResponseCode.TOKEN_INVALID.getMsg());
            }
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(jwt));
        }
        chain.doFilter(request, response);
    }


    /*******************************************************************************************/


    private UsernamePasswordAuthenticationToken getAuthentication(DecodedJWT jwt) {

        Long userId = jwt.getClaim(TokenUtil.USER_ID).asLong();
        String roleCodes = jwt.getClaim(TokenUtil.ROLES).asString();
        String userType = jwt.getClaim(TokenUtil.USER_TYPE).asString();
        String tokenType = jwt.getClaim(TokenUtil.TOKEN_TYPE).asString();

        // 检查token类型是否实 accessToken
        checkTokenType(tokenType);
        String clientId = jwt.getClaim(TokenUtil.CLIENT_ID).asString();
        String uid = jwt.getClaim(PublicClaims.JWT_ID).asString();
        List<GrantedAuthority> authorities = Arrays.stream(roleCodes.split(",")).filter(StringUtils::hasText)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        AuthInfo authInfo = AuthInfo.createTokenInfo(userId, userType, tokenType, roleCodes, clientId, uid);

        return new UsernamePasswordAuthenticationToken(userId, authInfo, authorities);
    }


    /**
     * 检查token类型
     *
     * @param tokenType token类型
     */
    public static void checkTokenType(String tokenType) {
        if (TokenType.ACCESS_TYPE.getType().equals(tokenType)) {
            ReportUtil.throwEx("token类型错误！");
        }
    }
}
