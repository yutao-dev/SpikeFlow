package com.spikeflow.common.exception.handler;

import com.spikeflow.common.exception.BaseException;
import com.spikeflow.common.model.bean.Result;
import com.spikeflow.common.model.enums.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
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

    /**
     * 处理数据库完整性约束异常
     * 捕获SQLIntegrityConstraintViolationException异常，处理数据库操作违反完整性约束的情况
     * 如主键冲突、外键约束违反等
     * 
     * @param exception 捕获的SQLIntegrityConstraintViolationException异常对象
     * @return 统一的错误响应结果，包含"数据库操作异常!"的错误消息
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result<String> handleSqlIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception) {
        log.error("数据库操作异常: {}", exception.getMessage());
        return Result.fail("数据库操作异常!");
    }
}
