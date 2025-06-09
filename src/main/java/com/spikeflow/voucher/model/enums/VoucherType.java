package com.spikeflow.voucher.model.enums;

import com.spikeflow.common.exception.VoucherException;
import com.spikeflow.common.model.enums.VoucherCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 优惠券类型枚举
 *
 * @author by 王玉涛
 * @EnumName VoucherType
 * @Description 定义系统中使用的优惠券类型枚举
 * @Date 2025/6/9 23:05
 */
@Getter
@AllArgsConstructor
public enum VoucherType {

    /**
     * 通用优惠券，适用于所有商品
     */
    COMMON_VOUCHER(0, "通用优惠券"),
    
    /**
     * 秒杀优惠券，仅适用于秒杀活动商品
     */
    SECKILL_VOUCHER(1, "秒杀优惠券");

    /**
     * 优惠券类型编码
     */
    private final Integer type;
    
    /**
     * 优惠券类型名称
     */
    private final String typeName;

    /**
     * 根据优惠券类型编码获取类型名称
     *
     * @param type 优惠券类型编码
     * @return String 对应的优惠券类型名称
     * @throws VoucherException 当传入的type不存在时抛出此异常，异常码为VOUCHER_TYPE_NOT_EXIST(2001)
     */
    public static String getTypeName(Integer type) {
        for (VoucherType value : VoucherType.values()) {
            if (value.getType().equals(type)) {
                return value.getTypeName();
            }
        }
        throw new VoucherException(VoucherCode.VOUCHER_TYPE_NOT_EXIST);
    }
}
