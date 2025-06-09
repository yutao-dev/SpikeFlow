package com.spikeflow.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * JWT配置类
 * @author 王玉涛
 * @version 1.0
 * @description 用于配置JWT相关参数，包括密钥和过期时间
 * @date 2025/6/9 21:04
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    /**
     * JWT加密密钥
     */
    private String secret;
    
    /**
     * JWT过期时间(毫秒)
     */
    private Long expiration;
}
