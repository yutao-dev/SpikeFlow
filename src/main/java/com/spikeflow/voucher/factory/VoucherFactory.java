package com.spikeflow.voucher.factory;

import com.spikeflow.common.util.SecurityUtils;
import com.spikeflow.order.model.bean.Order;
import com.spikeflow.user.model.bean.User;
import com.spikeflow.voucher.model.bean.Voucher;
import com.spikeflow.voucher.model.enums.OrderStatus;
import com.spikeflow.voucher.model.enums.VoucherStatus;
import com.spikeflow.voucher.model.enums.VoucherType;
import com.spikeflow.voucher.model.request.AddVoucherRequest;
import com.spikeflow.voucher.model.response.BuyVoucherResponse;
import com.spikeflow.voucher.model.response.CheckVoucherResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 优惠券工厂类，用于创建优惠券相关的响应对象
 * 
 * @author 王玉涛
 * @version 1.0
 * @since 2025/6/9
 */
public class VoucherFactory {
    /**
     * 批量创建优惠券响应对象列表
     * @param vouchers 优惠券实体列表
     * @return 优惠券响应对象列表
     */
    public static List<CheckVoucherResponse> createVoucherResponse(List<Voucher> vouchers) {
        return vouchers.stream()
                .map(VoucherFactory::createVoucherResponse)
                .toList();
    }

    /**
     * 创建单个优惠券响应对象
     * @param voucher 优惠券实体
     * @return 优惠券响应对象
     */
    public static CheckVoucherResponse createVoucherResponse(Voucher voucher) {
        return CheckVoucherResponse.builder()
                .id(voucher.getId())
                .title(voucher.getTitle())
                .type(VoucherType.getTypeName(voucher.getType()))
                .value(voucher.getValue())
                .rules(voucher.getRules())
                .stock(voucher.getStock())
                .beginTime(voucher.getBeginTime())
                .endTime(voucher.getEndTime())
                .status(VoucherStatus.getStatusName(voucher.getStatus()))
                .build();
    }

    /**
     * 创建优惠券实体
     * @param request
     * @return
     */
    public static Voucher createVoucher(AddVoucherRequest request) {
        return Voucher.builder()
                .title(request.getTitle())
                .type(request.getType())
                .value(request.getValue())
                .rules(request.getRules())
                .stock(request.getStock())
                .beginTime(request.getBeginTime())
                .endTime(request.getEndTime())
                .status(request.getStatus())
                .build();
    }

    /**
     * 创建优惠券订单
     * 根据优惠券信息生成对应的订单对象
     * 
     * @param voucher 优惠券实体对象
     * @return 创建的订单对象
     */
    public static Order createVoucherOrder(Voucher voucher) {
        // 获取当前时间用于订单创建和更新时间
        LocalDateTime now = LocalDateTime.now();
        
        // 获取当前登录用户信息
        User user = SecurityUtils.getUser();
        
        // 构建并返回订单对象
        return Order.builder()
                .userId(user.getId())
                .voucherId(voucher.getId())
                .amount(voucher.getValue())
                .actualPay(voucher.getValue())
                .discount(new BigDecimal(0))
                .status(OrderStatus.UNPAID.getStatus())
                .createdAt(now)
                .updatedAt(now)
                .build();
    }

    /**
     * 将订单对象转换为购买优惠券响应对象
     * 
     * @param voucherOrder 订单实体对象，包含订单详细信息
     * @return 构建完成的购买优惠券响应对象，包含订单ID、用户ID、优惠券ID、
     *         订单金额、优惠金额、实付金额、订单状态及创建更新时间等信息
     */
    public static BuyVoucherResponse OrderToResponse(Order voucherOrder) {
        return BuyVoucherResponse.builder()
                .id(voucherOrder.getId())
                .userId(voucherOrder.getUserId())
                .voucherId(voucherOrder.getVoucherId())
                .amount(voucherOrder.getAmount())
                .discount(voucherOrder.getDiscount())
                .actualPay(voucherOrder.getActualPay())
                .status(OrderStatus.getStatusName(voucherOrder.getStatus()))
                .createdAt(voucherOrder.getCreatedAt())
                .updatedAt(voucherOrder.getUpdatedAt())
                .build();
    }
}
