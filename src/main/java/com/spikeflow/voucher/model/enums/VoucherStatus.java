package com.spikeflow.voucher.model.enums;

import com.spikeflow.common.exception.VoucherException;
import com.spikeflow.common.model.enums.VoucherCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 凭证状态枚举类
 *
 * @author 王玉涛
 * @version 1.0
 * @since 2025/6/9
 */
@Getter
@AllArgsConstructor
public enum VoucherStatus {
    
    /**
     * 未启用状态，状态码为0
     */
    NOT_ENABLE(0, "未启用"),
    
    /**
     * 已启用状态，状态码为1
     */
    ENABLE(1, "已启用"),
    
    /**
     * 已过期状态，状态码为2
     */
    EXPIRED(2, "已过期");
    
    /**
     * 状态编码
     */
    private final Integer status;
    
    /**
     * 状态名称
     */
    private final String statusName;

    /**
     * 根据状态码获取对应的状态名称
     * 
     * @param status 状态编码，需匹配枚举中定义的状态码
     * @return String 返回对应的状态名称
     * @throws VoucherException 当传入的状态码不存在时抛出异常，错误码为VOUCHER_STATUS_NOT_EXIST
     */
    public static String getStatusName(Integer status) {
        for (VoucherStatus value : VoucherStatus.values()) {
            if (value.getStatus().equals(status)) {
                return value.getStatusName();
            }
        }
        throw new VoucherException(VoucherCode.VOUCHER_STATUS_NOT_EXIST);
    }
}
