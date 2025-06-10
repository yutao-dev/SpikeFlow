package com.spikeflow.voucher.model.enums;

import com.spikeflow.common.exception.OrderException;
import com.spikeflow.common.model.enums.OrderCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author by 王玉涛
 * @EnumName VoucherStatus
 * @Description TODO
 * @Date 2025/6/9 22:42
 */
@Getter
@AllArgsConstructor
public enum OrderStatus {

    // 1. 未支付
    UNPAID(1),
    // 2. 已支付
    PAID(2),
    // 3. 已取消
    CANCELED(3),
    // 4. 已退款
    REFUNDED(4);

    private final Integer status;

    public static String getStatusName(Integer status) {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.status.equals(status)) {
                return value.name();
            }
        }
        throw new OrderException(OrderCode.ORDER_STATUS_ERROR);
    }
}
