package com.spikeflow.voucher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spikeflow.voucher.model.bean.Voucher;
import com.spikeflow.voucher.model.request.AddVoucherRequest;
import com.spikeflow.voucher.model.response.CheckVoucherResponse;

import java.util.List;

/**
 * @author by 王玉涛
 * @Classname IVoucherService
 * @Description TODO
 * @Date 2025/6/9 22:51
 */
public interface IVoucherService extends IService<Voucher> {

    /**
     * 查询所有券
     * @return
     */
    List<CheckVoucherResponse> checkAllVoucher();

    /**
     * 添加券
     * @param request
     * @return
     */
    CheckVoucherResponse addVoucher(AddVoucherRequest request);
}
