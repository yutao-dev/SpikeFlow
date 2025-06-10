package com.spikeflow.common.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 券相关状态码枚举
 * 
 * @author by 王玉涛
 * @EnumName VoucherCode
 * @Description 定义系统所有与券相关的状态码和错误信息，
 *              错误码范围：2001-3000，每个错误码应在此范围内且唯一。
 *              实现StatusCode接口，提供统一的状态码规范。
 * @Date 2025/6/9 23:13
 * @Note 注意事项：
 *      1. 新增加错误码需在2001-3000范围内
 *      2. message描述应清晰准确，便于问题定位
 *      3. 默认使用UUID作为traceId，如需特殊需求可重写getTraceId()
 */
@Getter
@AllArgsConstructor
public enum VoucherCode implements StatusCode {
    
    // 券的错误码占2001 - 3000
    VOUCHER_TYPE_NOT_EXIST(2001, "券类型不存在！"),
    VOUCHER_NOT_EXIST(2002, "券不存在！"),
    VOUCHER_STATUS_NOT_EXIST(2003, "券状态不存在"),
    VOUCHER_SAVE_ERROR(2004, "优惠券保存失败！"),
    VOUCHER_DELETE_ERROR(2005, "优惠券删除失败！"),
    VOUCHER_ORDER_ERROR(2006, "优惠券下单失败！");
    
    private final Integer code;
    private final String message;
}
