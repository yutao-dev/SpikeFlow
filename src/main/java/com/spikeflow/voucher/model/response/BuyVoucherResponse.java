package com.spikeflow.voucher.model.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 购买优惠券响应对象
 * @author 王玉涛
 * @version 1.0
 * @description 用于返回用户购买优惠券的相关信息
 * @date 2025/6/10 20:01
 */
@Data
@Builder
public class BuyVoucherResponse {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 优惠券名称
     */
    private Long voucherId;

    /**
     * 订单金额(单位：元)
     */
    private BigDecimal amount;

    /**
     * 优惠金额(单位：元)
     */
    private BigDecimal discount;

    /**
     * 实际支付金额(单位：元)
     */
    private BigDecimal actualPay;

    /**
     * 订单状态
     * @see com.spikeflow.voucher.model.enums.OrderStatus
     */
    private String status;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
}
