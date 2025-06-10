package com.spikeflow.order.controller;

import com.spikeflow.common.model.bean.Result;
import com.spikeflow.order.service.IOrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by 王玉涛
 * @Classname OrderController
 * @Description TODO
 * @Date 2025/6/10 19:57
 */
@RestController
@RequestMapping("/orders")
@Tag(name = "订单管理", description = "订单的查询和添加接口")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final IOrderService orderService;

}
