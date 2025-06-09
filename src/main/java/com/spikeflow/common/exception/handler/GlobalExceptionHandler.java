package com.spikeflow.common.exception.handler;

import com.spikeflow.common.exception.BaseException;
import com.spikeflow.common.model.bean.Result;
import com.spikeflow.common.model.enums.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * 全局异常处理器
 * 
 * @author by 王玉涛
 * @Classname GlobalExceptionHandler
 * @Description 全局异常处理类，捕获并处理控制器层抛出的各种异常
 *              主要功能:
 *              1. 处理自定义BaseException
 *              2. 处理参数校验异常
 *              3. 统一返回格式并记录错误日志
 * @Date 2025/6/9 21:40
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    /**
     * 处理自定义业务异常
     * @param exception 捕获的BaseException异常
     * @return 统一的错误响应结果
     */
    @ExceptionHandler(BaseException.class)
    public Result<String> handleBaseException(BaseException exception) {
        StatusCode statusCode = exception.getStatusCode();
        Result<String> fail = Result.fail(statusCode);
        fail.logError();
        return fail;
    }

    /**
     * 处理参数校验异常
     * @param exception 捕获的参数校验异常
     * @return 统一的错误响应结果
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleValidationException(MethodArgumentNotValidException exception) {
        String errorMessage = exception.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        
        log.error("参数校验失败: {}", errorMessage);
        return Result.fail(errorMessage);
    }
}
