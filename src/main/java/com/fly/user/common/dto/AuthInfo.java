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
public class AuthInfo {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "账户类型 app:应用用户 web:后台管理")
    private String userType;

    @ApiModelProperty(value = "token类型")
    private String tokenType;

    @ApiModelProperty(value = "角色列表 role1,role2,role3....")
    private String roles;

    @ApiModelProperty(value = "客户端id")
    private String clientId;

    @ApiModelProperty(value = "唯一id")
    private String uid;


    public AuthInfo() {
    }

    /**
     * 创建token
     * @return tokenInfo
     */
    public static AuthInfo createTokenInfo(Long userId,
                                           String userType,
                                           String tokenType,
                                           String roles,
                                           String clientId,
                                           String uid){
        AuthInfo tokenInfo = new AuthInfo();
        tokenInfo.setUserId(userId);
        tokenInfo.setUserType(userType);
        tokenInfo.setTokenType(tokenType);
        tokenInfo.setRoles(roles);
        tokenInfo.setClientId(clientId);
        tokenInfo.setUid(uid);
        return tokenInfo;
    }
}
