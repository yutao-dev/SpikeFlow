package com.spikeflow.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author by 王玉涛
 * @Classname SecurityConfig
 * @Description 安全配置类，用于定义 Spring Security 的相关 Bean。
 *              此处主要配置了密码编码器（PasswordEncoder），用于支持安全的密码存储与验证。
 * @Date 2025/6/8 15:32
 */
@Configuration
public class SecurityConfig {
    
    /**
     * 配置 BCryptPasswordEncoder 实例作为密码编码器。
     * 使用强度为 12 的哈希成本因子，以平衡安全性与性能。
     * BCrypt 是一种基于 Blowfish 加密算法的强单向哈希函数，适合用于密码加密存储。
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
