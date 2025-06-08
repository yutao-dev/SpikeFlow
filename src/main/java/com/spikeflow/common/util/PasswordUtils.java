package com.spikeflow.common.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 密码工具类，用于处理密码加密和验证
 * 
 * @author 王玉涛
 * @Description 封装Spring Security的PasswordEncoder功能，提供密码加密和验证服务
 * @Date 2025/6/8 15:33
 * 
 * @Note 重要事项：
 * 1. 加密后的密码不可逆，请确保原始密码在加密前已妥善保存
 * 2. 每次加密同一个密码会生成不同的hash值，这是正常的安全特性
 * 3. 验证密码时需要提供原始密码和加密后的密码进行比对
 * 4. 使用的PasswordEncoder实现由Spring Security注入，需确保其配置正确
 */
@Component
@RequiredArgsConstructor
public class PasswordUtils {
    
    private final PasswordEncoder passwordEncoder;
    
    /**
     * 密码加密方法
     * @param password 原始密码
     * @return 加密后的密码字符串
     * @Note 注意：
     * 1. 不要对已经加密的密码再次加密
     * 2. 空密码会抛出异常，调用前需检查
     */
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
    
    /**
     * 密码验证方法
     * @param rawPassword 原始密码（未加密）
     * @param encodedPassword 已加密的密码
     * @return 是否匹配
     * @Note 注意：
     * 1. 验证失败不一定是密码错误，可能是加密算法不匹配
     * 2. 参数顺序很重要，第一个必须是原始密码
     */
    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
