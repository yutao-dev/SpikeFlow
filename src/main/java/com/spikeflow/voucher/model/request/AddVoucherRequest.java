package com.spikeflow.voucher.model.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author by 王玉涛
 * @Classname AddVoucherRequest
 * @Date 2025/6/9 23:32
 */
@Data
public class AddVoucherRequest {

    /**
     * 优惠券标题
     */
    private String title;

    /**
     * 优惠券类型
     */
    private Integer type;

    /**
     * 优惠券面值
     */
    private BigDecimal value;

    /**
     * 使用规则说明
     */
    private String rules;

    /**
     * 库存数量
     */
    private Integer stock;

    /**
     * 开始时间
     */
    private LocalDateTime beginTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 状态(0:未启用,1:已启用,2:已过期)
     */
    private Integer status;

}
