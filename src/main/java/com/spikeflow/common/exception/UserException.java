package com.spikeflow.common.exception;

import com.spikeflow.common.model.enums.StatusCode;

/**
 * @author by 王玉涛
 * @Classname UserException
 * @Description TODO
 * @Date 2025/6/9 21:38
 */
public class UserException extends BaseException {
    public UserException(StatusCode statusCode) {
        super(statusCode);
    }

    @Override
    public StatusCode getStatusCode() {
        return super.getStatusCode();
    }
}
