package com.spikeflow.common.model.bean;

import cn.hutool.json.JSONUtil;
import com.spikeflow.common.model.enums.StatusCode;
import com.spikeflow.common.model.enums.SystemCode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 通用响应结果封装类
 * 
 * @author by 王玉涛
 * @Classname Result
 * @Description 封装统一的API响应格式，包含状态码、消息、数据和追踪ID
 * @Date 2025/6/8 15:42
 */
@Data
@Slf4j
public class Result<T> {
    /**
     * 状态码，参考StatusCode接口规范
     */
    private Integer code;
    
    /**
     * 状态描述信息
     */
    private String message;
    
    /**
     * 响应数据
     */
    private T data;
    
    /**
     * 请求追踪ID，用于分布式系统跟踪
     */
    private String traceId;

    /**
     * 创建成功的响应结果
     * @param data 响应数据
     * @return 成功的结果对象
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        SystemCode success = SystemCode.SUCCESS;
        result.setCode(success.getCode());
        result.setMessage(success.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 创建失败的响应结果
     * @param statusCode 状态码枚举
     * @return 失败的结果对象
     */
    public static Result<String> fail(StatusCode statusCode) {
        Result<String> result = new Result<>();
        result.setCode(statusCode.getCode());
        result.setMessage(statusCode.getMessage());
        result.setData(null);
        result.setTraceId(statusCode.getTraceId());
        return result;
    }

    /**
     * 创建失败的响应结果
     * @param message 错误信息
     * @return 失败的结果对象
     */
    public static Result<String> fail(String message) {
        Result<String> result = new Result<>();
        SystemCode systemError = SystemCode.SYSTEM_ERROR;
        result.setCode(systemError.getCode());
        result.setMessage(message);
        result.setData(null);
        result.setTraceId(systemError.getTraceId());
        return result;
    }

    /**
     * 记录错误日志
     * 当响应结果不是成功状态时，记录详细的错误信息
     * 包含状态码、错误消息、响应数据和追踪ID
     * 成功状态则不做任何处理
     */
    public void logError() {
        if (SystemCode.SUCCESS.getCode().equals(code)) {
            return;
        }
        log.error("请求失败，状态码：{}，消息：{}，数据：{}，追踪ID：{}", code, message, data, traceId);
    }

    /**
     * 将当前Result对象转换为JSON字符串
     * 
     * @return 当前对象的JSON字符串表示形式
     */
    public String toJson() {
        return JSONUtil.toJsonStr(this);
    }
}
