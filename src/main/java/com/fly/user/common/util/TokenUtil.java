package com.fly.user.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.impl.PublicClaims;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fly.user.common.dto.AuthInfo;
import com.fly.user.common.dto.TokenInfo;
import com.fly.user.common.enums.ResponseCode;
import com.google.common.base.Charsets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xiao kun
 * @version 1.0
 * @since 2019-09-28 14:44
 */

public class TokenUtil {

    /**
     * admin token过期时间
     */
    public static final long ADMIN_ACCESS_EXPIRES = 604800000L;

    /**
     * admin 刷新token过期时间
     */
    public static final long ADMIN_REFRESH_EXPIRES = 606600000L;


    /**
     * 请求头属性名
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * token分割符号
     */
    public static final String TOKEN_SPLIT = "Bearer ";

    public static final String USER_ID = "userId";
    public static final String USER_TYPE = "userType";
    public static final String TOKEN_TYPE = "tokenType";
    public static final String ROLES = "roles";
    public static final String CLIENT_ID = "clientId";
    /**
     * token签名secret key
     */
    private static final byte[] SECRET = "MDk4ZjZiY2Q0NjIxYDM3M2NhXGU0ZTgzMjYyN2I0ZjY=".getBytes(Charsets.UTF_8);


    /**
     * 生产token
     *
     * @param tokenInfo tokenInfo
     * @return token
     */
    public static String createToken(TokenInfo tokenInfo) {

        //过期时间
        Date date = new Date(System.currentTimeMillis() + tokenInfo.getExpires());
        //秘钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        //设置头部信息
        Map<String, Object> header = new HashMap<>();
        header.put(PublicClaims.TYPE, "JWT");
        header.put(PublicClaims.ALGORITHM, "HS256");
        return JWT.create()
                .withHeader(header)
                .withIssuer("free loop")
                .withClaim(USER_ID, tokenInfo.getUserId())
                .withClaim(USER_TYPE, tokenInfo.getUserType())
                .withClaim(TOKEN_TYPE, tokenInfo.getTokenType().getType())
                .withClaim(ROLES, tokenInfo.getRoles())
                .withClaim(CLIENT_ID, tokenInfo.getClientId())
                .withJWTId(tokenInfo.getUid())
                .withIssuedAt(new Date())
                .withExpiresAt(date)
                .sign(algorithm);
    }


    /**
     * 解析token信息
     *
     * @param jwt jwt
     * @return authInfo
     */
    public static AuthInfo parseToken(DecodedJWT jwt) {
        Long userId = jwt.getClaim(TokenUtil.USER_ID).asLong();
        String roleCodes = jwt.getClaim(TokenUtil.ROLES).asString();
        String userType = jwt.getClaim(TokenUtil.USER_TYPE).asString();
        String tokenType = jwt.getClaim(TokenUtil.TOKEN_TYPE).asString();
        String clientId = jwt.getClaim(TokenUtil.CLIENT_ID).asString();
        String uid = jwt.getClaim(PublicClaims.JWT_ID).asString();
        return AuthInfo.createTokenInfo(userId, userType, tokenType, roleCodes, clientId, uid);
    }

    public static DecodedJWT verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        } catch (TokenExpiredException expiredException) {
            ReportUtil.throwEx(ResponseCode.TOKEN_TIME_OUT.getCode(), ResponseCode.TOKEN_TIME_OUT.getMsg());
        } catch (JWTVerificationException e) {
            ReportUtil.throwEx(ResponseCode.TOKEN_INVALID.getCode(), ResponseCode.TOKEN_INVALID.getMsg());
        }
        return null;
    }

}
