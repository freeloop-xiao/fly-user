package com.fly.user.common.dto;

import com.fly.user.common.enums.TokenType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/8/7 14:51
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="token生成信息", description="token生成信息")
public class TokenInfo {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "账户类型 app:应用用户 web:后台管理")
    private String userType;

    @ApiModelProperty(value = "token类型")
    private TokenType tokenType;

    @ApiModelProperty(value = "角色列表 role1,role2,role3....")
    private String roles;

    @ApiModelProperty(value = "客户端id")
    private String clientId;

    @ApiModelProperty(value = "唯一id")
    private String uid;

    @ApiModelProperty(value = "超时时间")
    private long expires;

    public TokenInfo() {
    }

    /**
     * 创建token
     * @return tokenInfo
     */
    public static TokenInfo createTokenInfo(Long userId,
                                            String userType,
                                            TokenType tokenType,
                                            String roles,
                                            String clientId,
                                            String uid,
                                            long expires){
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setUserId(userId);
        tokenInfo.setUserType(userType);
        tokenInfo.setTokenType(tokenType);
        tokenInfo.setRoles(roles);
        tokenInfo.setClientId(clientId);
        tokenInfo.setUid(uid);
        tokenInfo.setExpires(expires);
        return tokenInfo;
    }
}
