package com.spikeflow.common.filter;

import ch.qos.logback.core.util.StringUtil;
import com.spikeflow.common.model.bean.Result;
import com.spikeflow.common.model.constant.SystemConstant;
import com.spikeflow.common.model.enums.StatusCode;
import com.spikeflow.common.model.enums.SystemCode;
import com.spikeflow.common.util.JwtUtils;
import com.spikeflow.common.util.SecurityUtils;
import com.spikeflow.user.model.bean.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT认证过滤器
 * 
 * @author by 王玉涛
 * @Classname JwtFilter
 * @Description 用于处理JWT认证的过滤器，主要功能：
 *              1. 拦截请求并验证JWT令牌
 *              2. 解析JWT令牌获取用户信息
 *              3. 将用户信息存入SecurityContext
 *              4. 处理认证失败情况，返回标准错误响应
 * @Date 2025/6/9 22:01
 */
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    
    /**
     * JWT工具类，用于生成和解析JWT令牌
     */
    private final JwtUtils jwtUtils;

    /**
     * 过滤器的核心方法，处理每个HTTP请求
     * 
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @param filterChain 过滤器链
     * @throws ServletException 当处理请求发生错误时抛出
     * @throws IOException 当I/O操作发生错误时抛出
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求URI
        String requestUri = request.getRequestURI();
        
        // 如果是获取token的URI则直接放行
        if (SystemConstant.USER_TOKEN_URI.equals(requestUri)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 从请求头获取JWT令牌
        String jwt = request.getHeader(SystemConstant.AUTHORIZATION);
        if (StringUtil.isNullOrEmpty(jwt)) {
            responseReturnError(response, SystemCode.JWT_NOT_EXIST);
            return;
        }

        User user = null;
        try {
            // 解析JWT令牌获取用户信息
            user = jwtUtils.parseToken(jwt);
        } catch (Exception e) {
            responseReturnError(response, SystemCode.JWT_PARSE_ERROR);
            return;
        }

        // 将用户信息存入SecurityContext
        SecurityUtils.saveUser(user);
        
        // 继续执行过滤器链
        filterChain.doFilter(request, response);
    }

    /**
     * 返回错误响应
     * 
     * @param response HTTP响应对象
     * @param statusCode 状态码枚举
     * @throws IOException 当I/O操作发生错误时抛出
     */
    private static void responseReturnError(HttpServletResponse response, StatusCode statusCode) throws IOException {
        response.setStatus(statusCode.getCode());
        response.setContentType("application/json;charset=UTF-8");
        Result<String> fail = Result.fail(statusCode);
        response.getWriter().write(fail.toJson());
    }
}
