package com.spikeflow.common.exception;

import com.spikeflow.common.model.enums.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 基础异常类，所有业务异常应继承此异常
 * 
 * @author by 王玉涛
 * @Classname BaseException
 * @Description 系统基础异常类，封装了状态码规范接口(StatusCode)，
 *              用于统一处理系统中各类业务异常。
 *              主要特点：
 *              1. 继承RuntimeException，属于非检查型异常
 *              2. 包含状态码信息，便于异常处理和错误信息展示
 *              3. 通过@AllArgsConstructor自动生成全参构造器
 *              4. 通过@Getter自动生成getter方法
 * @Date 2025/6/9 21:38
 * @Note 使用说明：
 *      1. 业务模块自定义异常应继承此类
 *      2. 构造时应传入具体的StatusCode实现
 *      3. 可通过getStatusCode()获取异常状态信息
 */
@AllArgsConstructor
@Getter
public class BaseException extends RuntimeException {
    private StatusCode statusCode;
}
