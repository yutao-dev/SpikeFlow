package com.spikeflow.voucher.factory;

import com.spikeflow.voucher.model.bean.Voucher;
import com.spikeflow.voucher.model.enums.VoucherStatus;
import com.spikeflow.voucher.model.enums.VoucherType;
import com.spikeflow.voucher.model.request.AddVoucherRequest;
import com.spikeflow.voucher.model.response.CheckVoucherResponse;

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
}
