package com.spikeflow.common.config;

import com.spikeflow.common.filter.JwtFilter;
import com.spikeflow.common.model.bean.Result;
import com.spikeflow.common.model.enums.SystemCode;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author by 王玉涛
 * @Classname SecurityConfig
 * @Description 安全配置类，用于定义 Spring Security 的相关 Bean。
 *              主要包含以下配置：
 *              1. 密码编码器(PasswordEncoder) - 使用BCrypt算法加密存储密码
 *              2. 认证管理器(AuthenticationManager) - 处理用户认证请求
 *              3. 安全过滤器链(SecurityFilterChain) - 配置HTTP安全策略
 * @Date 2025/6/8 15:32
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    
    /**
     * 配置 BCryptPasswordEncoder 实例作为密码编码器。
     * 使用强度为 12 的哈希成本因子，以平衡安全性与性能。
     * BCrypt 是一种基于 Blowfish 加密算法的强单向哈希函数，适合用于密码加密存储。
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    /**
     * 配置认证管理器Bean，用于处理用户认证请求
     * @param configuration Spring提供的认证配置
     * @return 认证管理器实例
     */
    @SneakyThrows
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
        return configuration.getAuthenticationManager();
    }

    /**
     * 配置安全过滤器链，定义HTTP安全策略
     * 1. 禁用表单登录
     * 2. 允许匿名访问"/users/token"路径
     * 3. 其他所有请求需要认证
     * @param security HTTP安全配置对象
     * @return 安全过滤器链实例
     */
    @SneakyThrows
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) {
        return security
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/users/token").permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json;charset=UTF-8");
                            response.getWriter().write(Result.fail(SystemCode.SYSTEM_ERROR).toJson());
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType("application/json;charset=UTF-8");
                            response.getWriter().write(Result.fail(SystemCode.SYSTEM_ERROR).toJson());
                        })
                )
                .build();
    }
}
