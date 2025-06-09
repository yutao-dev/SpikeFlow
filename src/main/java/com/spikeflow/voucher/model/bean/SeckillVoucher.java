package com.spikeflow.voucher.model.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 秒杀优惠券实体类
 * 
 * @author 王玉涛
 * @version 1.0
 * @since 2025/6/9
 */
@Data
@TableName("seckill_voucher")
public class SeckillVoucher {
    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 每个用户限购数量
     */
    @TableField("limit_per_user")
    private Integer limitPerUser;

    /**
     * 秒杀开始时间
     */
    @TableField("start_time")
    private LocalDateTime startTime;

    /**
     * 秒杀结束时间
     */
    @TableField("end_time")
    private LocalDateTime endTime;
}
