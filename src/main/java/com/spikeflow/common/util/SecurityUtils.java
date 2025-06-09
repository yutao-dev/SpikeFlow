package com.spikeflow.common.util;

import com.spikeflow.user.factory.UserFactory;
import com.spikeflow.user.model.bean.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 安全工具类
 * 提供与Spring Security相关的操作
 *
 * @author 王玉涛
 * @version 1.0
 * @since 2025/6/9
 */
public class SecurityUtils {
    
    /**
     * 保存用户认证信息到安全上下文中
     * 该方法会将用户信息转换为Authentication对象并存入SecurityContext
     * 
     * @param user 用户对象，包含用户认证信息
     * @see UserFactory#createAuthentication(User)
     * @see SecurityContextHolder#getContext()
     */
    public static void saveUser(User user) {
        Authentication authentication = UserFactory.createAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * 获取当前登录用户的认证信息
     * @return
     */
    public static User getUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
