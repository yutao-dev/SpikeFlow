package com.spikeflow.voucher.controller;

import com.spikeflow.common.model.bean.Result;
import com.spikeflow.common.model.enums.VoucherCode;
import com.spikeflow.voucher.model.bean.Voucher;
import com.spikeflow.voucher.model.request.AddVoucherRequest;
import com.spikeflow.voucher.model.response.BuyVoucherResponse;
import com.spikeflow.voucher.model.response.CheckVoucherResponse;
import com.spikeflow.voucher.service.IVoucherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "优惠券管理", description = "优惠券的查询和添加接口")
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
    @Operation(summary = "查询所有优惠券", description = "获取系统中所有的优惠券信息")
    @ApiResponse(responseCode = "200", description = "成功获取优惠券列表")
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
    @Operation(summary = "添加优惠券", description = "向系统中添加新的优惠券")
    @ApiResponse(responseCode = "200", description = "成功添加优惠券")
    public Result<CheckVoucherResponse> addVoucher(@RequestBody AddVoucherRequest request) {
        log.info("添加优惠券: {}", request);
        CheckVoucherResponse response = voucherService.addVoucher(request);

        return Result.success(response);
    }

    /**
     * 删除优惠券
     * @param id 优惠券ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除优惠券", description = "从系统中删除指定的优惠券")
    @ApiResponse(responseCode = "200", description = "成功删除优惠券")
    public Result<String> deleteVoucher(@PathVariable Long id) {
        log.info("删除优惠券: {}", id);
        boolean result = voucherService.removeById(id);

        return result ? Result.success("删除成功") : Result.fail(VoucherCode.VOUCHER_DELETE_ERROR);
    }

    @PostMapping("/{id}/buy")
    @Operation(summary = "购买优惠券", description = "用户购买指定的优惠券")
    @ApiResponse(responseCode = "200", description = "成功购买优惠券")
    public Result<BuyVoucherResponse> buyVoucher(@PathVariable Long id) {
        log.info("购买优惠券: {}", id);

        BuyVoucherResponse response = voucherService.buyVoucher(id);

        return Result.success(response);
    }
}
