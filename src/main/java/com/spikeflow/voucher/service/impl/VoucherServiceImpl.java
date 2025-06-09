package com.spikeflow.voucher.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spikeflow.common.exception.VoucherException;
import com.spikeflow.common.model.enums.VoucherCode;
import com.spikeflow.voucher.factory.VoucherFactory;
import com.spikeflow.voucher.mapper.VoucherMapper;
import com.spikeflow.voucher.model.bean.Voucher;
import com.spikeflow.voucher.model.request.AddVoucherRequest;
import com.spikeflow.voucher.model.response.CheckVoucherResponse;
import com.spikeflow.voucher.service.IVoucherService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by 王玉涛
 * @Classname VoucherServiceImpl
 * @Description TODO
 * @Date 2025/6/9 22:52
 */
@Service
public class VoucherServiceImpl extends ServiceImpl<VoucherMapper, Voucher> implements IVoucherService {

    /**
     * 查询所有券
     *
     * @return
     */
    @Override
    public List<CheckVoucherResponse> checkAllVoucher() {
        List<Voucher> vouchers = list();
        vouchers = CollectionUtil.emptyIfNull(vouchers);

        return VoucherFactory.createVoucherResponse(vouchers);
    }

    /**
     * 添加券
     *
     * @param request
     * @return
     */
    @Override
    public CheckVoucherResponse addVoucher(AddVoucherRequest request) {
        Voucher voucher = VoucherFactory.createVoucher(request);

        boolean isSaved = save(voucher);
        if (!isSaved) {
            throw new VoucherException(VoucherCode.VOUCHER_SAVE_ERROR);
        }

        return VoucherFactory.createVoucherResponse(voucher);
    }
}
