package com.spikeflow.voucher.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spikeflow.common.exception.VoucherException;
import com.spikeflow.common.model.enums.VoucherCode;
import com.spikeflow.order.mapper.OrderMapper;
import com.spikeflow.order.model.bean.Order;
import com.spikeflow.voucher.factory.VoucherFactory;
import com.spikeflow.voucher.mapper.VoucherMapper;
import com.spikeflow.voucher.model.bean.Voucher;
import com.spikeflow.voucher.model.request.AddVoucherRequest;
import com.spikeflow.voucher.model.response.BuyVoucherResponse;
import com.spikeflow.voucher.model.response.CheckVoucherResponse;
import com.spikeflow.voucher.service.IVoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by 王玉涛
 * @Classname VoucherServiceImpl
 * @Description TODO
 * @Date 2025/6/9 22:52
 */
@Service
@RequiredArgsConstructor
public class VoucherServiceImpl extends ServiceImpl<VoucherMapper, Voucher> implements IVoucherService {

    private final VoucherMapper voucherMapper;
    private final OrderMapper orderMapper;


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

    /**
     * 购买券
     * @param id
     * @return
     */
    @Override
    public BuyVoucherResponse buyVoucher(Long id) {
        // 1. 查询券是否存在
        Voucher voucher = voucherMapper.selectById(id);
        // 2. 进行下单
        Order voucherOrder = VoucherFactory.createVoucherOrder(voucher);
        int insert = orderMapper.insert(voucherOrder);

        if (insert <= 0) {
            throw new VoucherException(VoucherCode.VOUCHER_ORDER_ERROR);
        }

        // 3. 封装返回
        return VoucherFactory.OrderToResponse(voucherOrder);
    }
}
