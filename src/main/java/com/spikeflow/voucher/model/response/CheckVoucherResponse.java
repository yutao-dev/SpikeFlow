package com.spikeflow.voucher.model.response;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author by 王玉涛
 * @Classname CheckVoucherResponse
 * @Description TODO
 * @Date 2025/6/9 23:01
 */
@Builder
@Data
public class CheckVoucherResponse {
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
     * 优惠券类型 (转化为String类型)
     */
    private String type;

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
     * 状态(0:未启用,1:已启用,2:已过期) (转化为字符串)
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
