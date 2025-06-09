package com.spikeflow.voucher.model.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.spikeflow.voucher.model.enums.VoucherType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 优惠券实体类
 * 
 * @author 王玉涛
 * @version 1.0
 * @since 2025/6/9
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("voucher")
public class Voucher {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

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
    @TableField("begin_time")
    private LocalDateTime beginTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    private LocalDateTime endTime;

    /**
     * 状态(0:未启用,1:已启用,2:已过期)
     */
    private Integer status;

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