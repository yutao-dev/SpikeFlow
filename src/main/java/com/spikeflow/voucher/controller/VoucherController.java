package com.spikeflow.voucher.controller;

import com.spikeflow.common.model.bean.Result;
import com.spikeflow.voucher.model.bean.Voucher;
import com.spikeflow.voucher.model.request.AddVoucherRequest;
import com.spikeflow.voucher.model.response.CheckVoucherResponse;
import com.spikeflow.voucher.service.IVoucherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 优惠券控制器
 * 
 * @author by 王玉涛
 * @Classname VoucherController
 * @Description 处理优惠券相关的HTTP请求，包括查询所有优惠券等操作
 * @Date 2025/6/9 22:51
 */
@RestController
@RequestMapping("/vouchers")
@Slf4j
@RequiredArgsConstructor
public class VoucherController {

    /**
     * 优惠券服务接口
     */
    private final IVoucherService voucherService;

    /**
     * 获取所有优惠券信息
     * 
     * @return Result<List<CheckVoucherResponse>> 包含优惠券列表的响应结果
     */
    @GetMapping
    public Result<List<CheckVoucherResponse>> checkAllVoucher() {
        log.info("获取所有优惠券");
        List<CheckVoucherResponse> responses = voucherService.checkAllVoucher();

        return Result.success(responses);
    }

    /**
     * 添加优惠券
     * @param request 请求参数
     * @return 操作结果
     */
    @PostMapping
    public Result<CheckVoucherResponse> addVoucher(@RequestBody AddVoucherRequest request) {
        log.info("添加优惠券: {}", request);
        CheckVoucherResponse response = voucherService.addVoucher(request);

        return Result.success(response);
    }
}
