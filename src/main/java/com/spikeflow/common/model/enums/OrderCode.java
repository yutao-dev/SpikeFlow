package com.spikeflow.common.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态错误码枚举
 * 
 * @author by 王玉涛
 * @EnumName OrderCode
 * @Description 定义订单状态相关的错误码
 * @Date 2025/6/10 20:15
 */
@Getter
@AllArgsConstructor
public enum OrderCode implements StatusCode {
    
    /**
     * 订单状态错误
     * 错误码范围: 3001 - 4000
     * 错误描述: 订单当前状态不符合操作要求
     */
    ORDER_STATUS_ERROR(3001, "订单状态异常");
    
    /**
     * 错误码
     */
    private final Integer code;
    
    /**
     * 错误信息描述
     */
    private final String message;
}
