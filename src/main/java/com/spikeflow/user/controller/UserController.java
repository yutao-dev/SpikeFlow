package com.spikeflow.user.controller;

import com.spikeflow.common.model.bean.Result;
import com.spikeflow.user.model.request.UsersTokenRequest;
import com.spikeflow.user.model.response.UsersMeResponse;
import com.spikeflow.user.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 * 
 * @author by 王玉涛
 * @Classname UserController
 * @Description 处理用户相关的HTTP请求，包括登录等操作
 * @Date 2025/6/9 21:29
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "用户管理", description = "用户登录和个人信息管理接口")
public class UserController {

    /**
     * 用户服务接口
     */
    private final IUserService userService;

    /**
     * 用户登录接口
     * @param request 包含用户名和密码的请求体
     * @return 包含JWT令牌的响应结果
     * @apiNote POST /users/token
     * @throws jakarta.validation.ValidationException 如果请求参数验证失败
     */
    @Operation(summary = "用户登录", description = "使用用户名和密码获取JWT令牌")
    @PostMapping("/token")
    public Result<String> login(@Validated @RequestBody UsersTokenRequest request) {
        log.info("用户登录: {}", request.getUsername());
        String jwt = userService.login(request);

        return Result.success(jwt);
    }

    /**
     * 获取当前登录用户信息接口
     * @return 包含当前用户信息的响应结果
     * @apiNote GET /users/me
     * 响应数据包含用户ID、用户名和头像URL等信息
     */
    @Operation(summary = "获取当前用户信息", description = "获取当前登录用户的基本信息")
    @GetMapping("/me")
    public Result<UsersMeResponse> me() {
        
        UsersMeResponse response = userService.me();
        
        return Result.success(response);
    }
}
