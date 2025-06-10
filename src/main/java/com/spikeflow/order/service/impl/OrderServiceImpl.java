package com.spikeflow.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spikeflow.order.mapper.OrderMapper;
import com.spikeflow.order.model.bean.Order;
import com.spikeflow.order.service.IOrderService;
import org.springframework.stereotype.Service;

/**
 * @author by 王玉涛
 * @Classname OrderServiceImpl
 * @Description TODO
 * @Date 2025/6/10 19:57
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
}
