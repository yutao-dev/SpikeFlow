package com.spikeflow.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spikeflow.order.model.bean.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author by 王玉涛
 * @Classname OrdersMapper
 * @Description TODO
 * @Date 2025/6/9 22:50
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
