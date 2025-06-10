package com.spikeflow.common.model.constant;

import java.util.List;

/**
 * @author by 王玉涛
 * @Classname SystemConstant
 * @Description TODO
 * @Date 2025/6/9 22:02
 */
public class SystemConstant {
    public static final String USER_TOKEN_URI = "/users/token";
    public static final String AUTHORIZATION = "Authorization";
    public static final List<String> ACCESS_URI = List.of(
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/users/token",
            "doc.html"
    );
    public static final String NORMAL_ENVIRONMENT = "normal";
    public static final String MOCK_ENVIRONMENT = "mock";
}
