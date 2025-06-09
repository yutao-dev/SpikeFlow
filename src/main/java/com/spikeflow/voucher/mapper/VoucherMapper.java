package com.spikeflow.voucher.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spikeflow.voucher.model.bean.Voucher;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author by 王玉涛
 * @InterfaceName VoucherMapper
 * @Description TODO
 * @Date 2025/6/9 22:51
 */
@Mapper
public interface VoucherMapper extends BaseMapper<Voucher> {
}
