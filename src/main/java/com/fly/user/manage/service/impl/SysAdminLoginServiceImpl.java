package com.fly.user.manage.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.util.IdUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fly.user.common.dto.AuthInfo;
import com.fly.user.common.dto.TokenInfo;
import com.fly.user.common.enums.ResponseCode;
import com.fly.user.common.enums.TokenType;
import com.fly.user.common.enums.UserType;
import com.fly.user.common.util.ReportUtil;
import com.fly.user.common.util.TokenUtil;
import com.fly.user.common.vo.TokenVO;
import com.fly.user.manage.pojo.dto.SysAdminLoginRequest;
import com.fly.user.manage.pojo.entity.SysAdmin;
import com.fly.user.manage.pojo.entity.SysRole;
import com.fly.user.manage.pojo.vo.ImageVO;
import com.fly.user.manage.service.SysAdminLoginService;
import com.fly.user.manage.service.SysAdminService;
import com.fly.user.manage.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/8/7 13:35
 */
@Service
public class SysAdminLoginServiceImpl implements SysAdminLoginService {


    /**
     * 图像返回字符串头
     */
    private static final String IMAGE_HEADER = "data:image/jpg;base64,";

    /**
     * 刷新token前缀
     */
    private static final String REFRESH_KEY = "refresh:";

    /**
     * 超时时间
     */
    private static final long TIME_OUT = 120L;

    @Autowired
    private SysAdminService sysAdminService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public ImageVO captcha() {
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);
        String imageStr = IMAGE_HEADER + captcha.getImageBase64();
        ImageVO imageVO = new ImageVO(IdUtil.fastSimpleUUID(), imageStr);
        // 保存redis中 设置ttl
        redisTemplate.opsForValue().set(imageVO.getSerNo(), captcha.getCode(), TIME_OUT, TimeUnit.SECONDS);
        imageVO.setCode(captcha.getCode());
        return imageVO;
    }

    @Override
    public TokenVO login(SysAdminLoginRequest request) {

        // 校验 验证码
        checkCaptcha(request.getCaptchaSerNo(), request.getCaptcha());

        // 校验登陆条件
        SysAdmin sysAdmin = checkLoginCondition(request);

        // 查询用户角色
        String role = joinRoles(sysAdmin.getUserId());
        TokenInfo tokenInfo = TokenInfo.createTokenInfo(sysAdmin.getUserId(),
                UserType.ADMIN.getType(),
                TokenType.ACCESS_TYPE,
                role,
                sysAdmin.getAppId(),
                IdUtil.fastSimpleUUID(),
                TokenUtil.ADMIN_ACCESS_EXPIRES);
        return createTokenVO(tokenInfo);
    }

    @Override
    public TokenVO refresh(String refreshToken) {

        AuthInfo authInfo = checkRefreshToken(refreshToken);

        TokenInfo tokenInfo = TokenInfo.createTokenInfo(authInfo.getUserId(),
                UserType.ADMIN.getType(),
                TokenType.ACCESS_TYPE,
                authInfo.getRoles(),
                authInfo.getClientId(),
                IdUtil.fastSimpleUUID(),
                TokenUtil.ADMIN_ACCESS_EXPIRES);
        return createTokenVO(tokenInfo);
    }


    /********************************************************************************/


    /**
     * 刷新token检测是否有效
     *
     * @param refreshToken refreshToken
     */
    private AuthInfo checkRefreshToken(String refreshToken) {

        DecodedJWT jwt = TokenUtil.verify(refreshToken);

        if (jwt == null) {
            ReportUtil.throwEx(ResponseCode.TOKEN_INVALID.getCode(), ResponseCode.TOKEN_INVALID.getMsg());
        }

        AuthInfo authInfo = TokenUtil.parseToken(jwt);

        String uid = redisTemplate.opsForValue().get(REFRESH_KEY + authInfo.getUserId());
        if (!authInfo.getUid().equals(uid)) {
            ReportUtil.throwEx(ResponseCode.TOKEN_INVALID.getCode(), ResponseCode.TOKEN_INVALID.getMsg());
        }

        return authInfo;
    }


    /**
     * 创建token
     *
     * @param tokenInfo tokenInfo
     * @return tokenVO
     */
    private TokenVO createTokenVO(TokenInfo tokenInfo) {
        TokenVO tokenVO = new TokenVO();
        tokenVO.setAccessToken(TokenUtil.createToken(tokenInfo));
        tokenInfo.setExpires(TokenUtil.ADMIN_REFRESH_EXPIRES);
        tokenInfo.setTokenType(TokenType.REFRESH_TYPE);
        tokenVO.setRefreshToken(TokenUtil.createToken(tokenInfo));
        tokenVO.setExpires(System.currentTimeMillis() + TokenUtil.ADMIN_ACCESS_EXPIRES);
        saveRefreshToken(tokenInfo);
        return tokenVO;
    }

    /**
     * 验证图形验证码
     *
     * @param serNo 序号
     * @param code  图片显示码
     */
    private void checkCaptcha(String serNo, String code) {

        String savedCode = redisTemplate.opsForValue().get(serNo);
        if (StringUtils.isEmpty(serNo)) {
            ReportUtil.throwEx("验证码过期");
        }

        redisTemplate.delete(serNo);
        if (!code.equals(savedCode)) {
            ReportUtil.throwEx("验证码错误");
        }
    }

    /**
     * 校验登陆条件
     *
     * @param request 请求对象
     * @return SysAdmin
     */
    private SysAdmin checkLoginCondition(SysAdminLoginRequest request) {
        String phone = request.getPhone().trim();

        // 查询用户
        SysAdmin sysAdmin = sysAdminService.getAdminByPhone(phone);

        // 判断用户是否存在
        if (sysAdmin == null || sysAdmin.getDelFlag()) {
            ReportUtil.throwEx("用户不存在");
        }

        // 判断用户是否锁定
        if (sysAdmin.getIsLocked()) {
            ReportUtil.throwEx("用户已经锁定，请联系管理员");
        }
        String password = request.getPassword().trim();
        if (!bCryptPasswordEncoder.matches(password, sysAdmin.getPassword())) {
            ReportUtil.throwEx("密码错误");
        }
        return sysAdmin;
    }


    private void saveRefreshToken(TokenInfo tokenInfo) {
        redisTemplate.opsForValue().set(REFRESH_KEY + tokenInfo.getUserId(), tokenInfo.getUid(), TokenUtil.ADMIN_REFRESH_EXPIRES, TimeUnit.MILLISECONDS);
    }

    private String joinRoles(Long userId) {

        List<SysRole> roles = sysRoleService.getSysAdminRoles(userId);
        if (roles.size() == 0) {
            return null;
        }
        StringBuilder roleSb = new StringBuilder();
        for (SysRole sysRole : roles) {
            roleSb.append(sysRole.getRoleCode()).append(",");
        }
        String role = roleSb.toString();
        return role.substring(0, role.lastIndexOf(","));
    }
}
