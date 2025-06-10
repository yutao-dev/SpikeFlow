package com.spikeflow.common.util;

import java.util.List;

/**
 * 字符串工具类
 * 
 * @author 王玉涛
 * @version 1.0
 * @since 2025/6/10
 */
public class StringUtils {

    /**
     * 检查URI是否匹配给定的URI模式列表
     * 
     * @param uris URI模式列表，支持通配符*和**（但会被移除后再匹配）
     * @param uri 要检查的URI
     * @return true如果URI匹配任一模式，false如果都不匹配或输入为null
     * 
     * @apiNote
     * - 模式匹配规则：将模式中的*和**移除后，检查URI是否以处理后的模式开头
     * - 示例：模式"/api/**"会被处理为"/api/"，URI"/api/users"会匹配
     * - 输入参数为null时直接返回false
     */
    public static boolean uriPattern(List<String> uris, String uri) {
        if (uris == null || uri == null) {
            return false;
        }
        for (String accessUri : uris) {
            if (accessUri == null) {
                continue;
            }
            String processedUri = accessUri.replace("**", "").replace("*", "");
            if (uri.startsWith(processedUri)) {
                return true;
            }
        }
        return false;
    }
}
