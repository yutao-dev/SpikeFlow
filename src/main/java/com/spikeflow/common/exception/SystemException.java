package com.spikeflow.common.exception;

import com.spikeflow.common.model.enums.StatusCode;

/**
 * 系统异常类，用于表示系统级别的异常情况
 * 
 * @author by 王玉涛
 * @Classname SystemException
 * @Description 系统核心异常类，继承自BaseException，用于处理系统层面的异常情况
 *              如：系统初始化失败、核心组件异常、关键资源不可用等
 *              主要特点：
 *              1. 继承BaseException，具备统一的状态码规范
 *              2. 用于系统关键路径上的异常处理
 *              3. 通过状态码可快速定位系统问题
 * @Date 2025/6/9 21:39
 * @Note 使用说明：
 *      1. 适用于系统核心模块的异常处理
 *      2. 构造时需传入明确的StatusCode实现
 *      3. 建议配合系统状态码枚举(SystemStatusCode)使用
 */
public class SystemException extends BaseException{
    /**
     * 构造方法，传入状态码对象
     * @param statusCode 状态码对象，需实现StatusCode接口
     */
    public SystemException(StatusCode statusCode) {
        super(statusCode);
    }

    /**
     * 获取异常关联的状态码信息
     * @return 状态码对象，包含错误码和错误信息
     */
    @Override
    public StatusCode getStatusCode() {
        return super.getStatusCode();
    }
}
