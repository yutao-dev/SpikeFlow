package com.spikeflow.user.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户令牌请求实体类
 * 
 * @author by 王玉涛
 * @Classname UsersTokenRequest
 * @Description 用于接收用户登录时的用户名和密码
 * @Date 2025/6/9 21:30
 */
@Data
@AllArgsConstructor
public class UsersTokenRequest {
    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空！")
    private String username;
    
    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空！")
    private String password;
}
