package com.spikeflow.common.exception;

import com.spikeflow.common.model.enums.StatusCode;

/**
 * @author by 王玉涛
 * @Classname OrderException
 * @Description TODO
 * @Date 2025/6/10 20:15
 */
public class OrderException extends BaseException {
    public OrderException(StatusCode statusCode) {
        super(statusCode);
    }

    @Override
    public StatusCode getStatusCode() {
        return super.getStatusCode();
    }
}
