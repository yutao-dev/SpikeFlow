package com.spikeflow.user.factory;

import com.spikeflow.user.model.bean.User;
import com.spikeflow.user.model.response.UsersMeResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

/**
 * 用户工厂类，用于处理用户相关操作
 *
 * @author 王玉涛
 * @version 1.0
 * @since 2025/6/9
 */
public class UserFactory {
    
    /**
     * 用户信息脱敏处理
     * 保留非敏感信息：用户ID、用户名
     * 过滤掉敏感信息：密码、邮箱、手机号等
     *
     * @param user 原始用户对象
     * @return 脱敏后的用户对象
     */
    public static User desensitization(User user) {
        return User.builder()
                .id(user.getId())
                .roles(user.getRoles())
                .username(user.getUsername())
                .build();
    }

    /**
     * 创建认证信息对象
     *
     * @param user 用户对象
     * @return 认证信息对象
     */
    public static Authentication createAuthentication(User user) {
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    /**
     * 创建用户个人信息响应对象
     * 用于构建返回给前端的安全用户信息，包含用户基本非敏感信息
     * 
     * @param user 原始用户对象，包含用户所有信息
     * @return UsersMeResponse 包含用户ID、用户名和头像的响应对象
     */
    public static UsersMeResponse createMeResponse(User user) {
        return UsersMeResponse .builder()
                .id(user.getId())
                .avatar(user.getAvatar())
                .username(user.getUsername())
                .build();
    }
}
