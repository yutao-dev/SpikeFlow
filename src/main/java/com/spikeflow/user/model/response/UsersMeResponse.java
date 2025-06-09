package com.spikeflow.user.model.response;

import lombok.Builder;
import lombok.Data;

/**
 * 用户个人信息响应类
 * 
 * @author 王玉涛
 * @Classname UsersMeResponse
 * @Description 包含用户基本信息的响应对象，用于返回当前登录用户的个人信息
 * @Date 2025/6/9 22:13
 */
@Data
@Builder
public class UsersMeResponse {
    /** 用户唯一标识ID */
    private Long id;
    /** 用户名 */
    private String username;
    /** 用户头像URL */
    private String avatar;
}
