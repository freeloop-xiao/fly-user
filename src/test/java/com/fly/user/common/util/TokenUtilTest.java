package com.fly.user.common.util;

import cn.hutool.core.util.IdUtil;
import com.auth0.jwt.impl.PublicClaims;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fly.user.common.dto.TokenInfo;
import com.fly.user.common.enums.TokenType;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/8/7 15:41
 */
public class TokenUtilTest {

    @Test
    public void createToken() {
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setClientId("client");
        tokenInfo.setUserId(1L);
        tokenInfo.setUserType("user");
        tokenInfo.setTokenType(TokenType.ACCESS_TYPE);
        tokenInfo.setRoles("role1,role2,role3");
        tokenInfo.setUid(IdUtil.fastSimpleUUID());
        tokenInfo.setExpires(1000000L);
        String token = TokenUtil.createToken(tokenInfo);
        System.out.println(token);
        DecodedJWT decodedJWT = TokenUtil.verify(token);
        assert decodedJWT != null;
        System.out.println("userId:" + decodedJWT.getClaim("userId").asString());
        System.out.println("roles:" + decodedJWT.getClaim("roles").asString());
        System.out.println("userType:" + decodedJWT.getClaim("userType").asString());
        System.out.println("type:" + decodedJWT.getClaim("type").asString());
        System.out.println("tokenType:" + decodedJWT.getClaim("tokenType").asString());
        System.out.println(PublicClaims.JWT_ID+":"+decodedJWT.getClaim(PublicClaims.JWT_ID).asString());
    }

    @Test
    public void verify() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjbGllbnRJZCI6ImNsaWVudCIsInJvbGVzIjoicm9sZTEscm9sZTIscm9sZTMiLCJpc3MiOiJmcmVlIGxvb3AiLCJ1c2VyVHlwZSI6InVzZXIiLCJ0b2tlblR5cGUiOiJhY2Nlc3MiLCJleHAiOjE1OTY3ODc5OTcsInVzZXJJZCI6IjIxZmMyMjI5YjdlMTQxYzBhNTBlM2U0Zjc3ZWIzMjQ0IiwiaWF0IjoxNTk2Nzg2OTk3fQ.UE9sGkQEFh_NnbfW0ksSrSO_V5uq8T4Ljdh4VHjPE3k";
        TokenUtil.verify(token);
    }

    @Test
    public void tokenParse(){
        String token = "Bearser eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjbGllbnRJZCI6ImNsaWVudCIsInJvbGVzIjoicm9sZTEscm9sZTIscm9sZTMiLCJpc3MiOiJmcmVlIGxvb3AiLCJ1c2VyVHlwZSI6InVzZXIiLCJ0b2tlblR5cGUiOiJhY2Nlc3MiLCJleHAiOjE1OTY3ODc5OTcsInVzZXJJZCI6IjIxZmMyMjI5YjdlMTQxYzBhNTBlM2U0Zjc3ZWIzMjQ0IiwiaWF0IjoxNTk2Nzg2OTk3fQ.UE9sGkQEFh_NnbfW0ksSrSO_V5uq8T4Ljdh4VHjPE3k";
        String[] temp = token.split(TokenUtil.TOKEN_SPLIT);
        String temps = token.substring(TokenUtil.TOKEN_SPLIT.length());
        System.out.println("temps:"+temps);
        System.out.println("0:"+temp[0]);
        System.out.println("1:"+temp[1]);

    }
}