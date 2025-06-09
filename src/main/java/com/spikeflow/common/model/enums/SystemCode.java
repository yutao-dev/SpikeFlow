package com.spikeflow.common.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统响应状态码枚举类
 * 
 * @author 王玉涛
 * @Classname SystemCode
 * @Description 定义系统统一的响应状态码和提示信息。需要注意：
 *             1. 系统错误码范围应保持在0-1000之间，便于统一管理
 *             2. 成功状态使用200系列，客户端错误使用400系列，服务器错误使用500系列
 *             3. 新增错误码时需保持语义明确，避免重复
 *             4. message信息应简洁明了，便于用户理解
 * @Date 2025/6/8 15:38
 */
@Getter
@AllArgsConstructor
public enum SystemCode implements StatusCode {

    // 标准HTTP状态码定义
    SUCCESS(200, "请求成功！"),
    SYSTEM_ERROR(500, "服务器内部错误，请联系管理员"),
    PARAM_ERROR(400, "请求参数校验失败，请检查参数格式"),
    JWT_PARSE_ERROR(900, "JWT解析异常！"),
    JWT_NOT_EXIST(901, "JWT不存在！");

    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 状态码描述
     */
    private final String message;
}
