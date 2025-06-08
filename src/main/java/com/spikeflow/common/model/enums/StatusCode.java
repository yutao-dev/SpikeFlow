package com.spikeflow.common.model.enums;

import java.util.UUID;

/**
 * 状态码规范接口，定义系统统一的状态码规范
 * 
 * @author by 王玉涛
 * @InterfaceName StatusCode
 * @Description 该接口定义了系统统一的状态码规范，所有业务模块的状态码枚举都应实现此接口。
 *              主要作用：
 *              1. 统一状态码格式(getCode)
 *              2. 统一状态信息格式(getMessage)
 *              3. 提供默认的请求追踪ID生成方法(getTraceId)
 * @Date 2025/6/8 15:36
 * @Note 注意事项：
 *      1. 实现类应确保每个状态码是唯一的
 *      2. getMessage()返回的描述应当简明且有意义
 *      3. traceId可用于分布式系统请求追踪，默认实现使用UUID，
 *         如需特殊需求可重写该方法
 */
public interface StatusCode {
    /**
     * 获取状态码
     * @return 状态码数字，应保证在系统范围内唯一
     */
    Integer getCode();
    
    /**
     * 获取状态描述信息
     * @return 状态描述，应简明扼要说明状态含义
     */
    String getMessage();
    
    /**
     * 获取请求追踪ID
     * @return 追踪ID字符串，默认实现使用UUID.randomUUID()
     * @default 默认使用UUID生成，可重写此方法提供自定义实现
     */
    default String getTraceId() {
        return UUID.randomUUID().toString();
    }
}
