package com.spikeflow.common.exception;

import com.spikeflow.common.model.enums.StatusCode;

/**
 * 凭证相关业务异常类
 * 
 * @author by 王玉涛
 * @Classname VoucherException
 * @Description 处理凭证相关的业务异常，如：
 *              1. 凭证创建失败
 *              2. 凭证验证失败
 *              3. 凭证状态异常
 *              4. 凭证过期等业务场景
 * @Date 2025/6/9 23:12
 * @Note 使用说明：
 *      1. 需要传入具体的StatusCode实现类实例
 *      2. 继承自BaseException，属于非检查型异常
 *      3. 可通过getStatusCode()获取异常状态信息
 */
public class VoucherException extends BaseException {
    /**
     * 构造方法
     * @param statusCode 状态码枚举实例，需实现StatusCode接口
     */
    public VoucherException(StatusCode statusCode) {
        super(statusCode);
    }

    /**
     * 获取异常状态码信息
     * @return StatusCode 状态码接口实例
     */
    @Override
    public StatusCode getStatusCode() {
        return super.getStatusCode();
    }
}
