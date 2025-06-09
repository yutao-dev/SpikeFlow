package com.spikeflow.common.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户相关状态码枚举
 * 
 * @author by 王玉涛
 * @EnumName UserCode
 * @Description 用户模块状态码定义，实现了StatusCode接口，遵循系统统一的状态码规范
 *              主要包含以下错误码：
 *              1001 - 用户不存在
 *              1002 - 用户已存在
 * @Date 2025/6/9 21:52
 * @Note 注意事项：
 *      1. 状态码在用户模块内保持唯一
 *      2. 状态描述应当清晰表达错误原因
 *      3. 默认使用父接口的traceId生成方式
 */
@Getter
@AllArgsConstructor
public enum UserCode implements StatusCode{

    // 状态码
    USER_NOT_EXIST(1001, "用户不存在！"),
    USER_EXIST(1002, "用户已存在！");
    
    
    private final Integer code;
    private final String message;
}
