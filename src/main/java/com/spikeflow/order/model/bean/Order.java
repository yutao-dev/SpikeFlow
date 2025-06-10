package com.spikeflow.order.model.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单实体类
 * 
 * @author 王玉涛
 * @version 1.0
 * @since 2025/6/9
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("orders")
public class Order {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 优惠券ID
     */
    @TableField("voucher_id")
    private Long voucherId;

    /**
     * 订单金额
     */
    private BigDecimal amount;

    /**
     * 优惠金额
     */
    private BigDecimal discount;

    /**
     * 实际支付金额
     */
    @TableField("actual_pay")
    private BigDecimal actualPay;

    /**
     * 订单状态
     */
    private Integer status;

    /**
     * 支付时间
     */
    @TableField("payment_time")
    private LocalDateTime paymentTime;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}