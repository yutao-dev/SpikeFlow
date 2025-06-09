package com.spikeflow.common.util;

import cn.hutool.json.JSONUtil;
import com.spikeflow.common.config.JwtConfig;
import com.spikeflow.user.factory.UserFactory;
import com.spikeflow.user.model.bean.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * JWT工具类
 * @author 王玉涛
 * @version 1.0
 * @description 使用jjwt 0.12.5实现JWT的生成和验证功能
 * @date 2025/6/9 21:08
 */
@Component
@RequiredArgsConstructor
public class JwtUtils {
    
    private final JwtConfig jwtConfig;
    
    /**
     * 生成JWT token
     * @param user 用户对象
     * @return JWT token
     */
    public String generateToken(User user) {
        user = UserFactory.desensitization(user);
        String userStr = JSONUtil.toJsonStr(user);
        return Jwts.builder()
                .subject(userStr)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
                .signWith(Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8)), Jwts.SIG.HS256)
                .compact();
    }
    
    /**
     * 解析JWT token
     * @param token JWT token
     * @return 解析后的JWT subject User
     */
    public User parseToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return JSONUtil.toBean(claims.getSubject(), User.class);
    }
}
