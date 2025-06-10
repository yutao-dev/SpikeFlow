package com.spikeflow.test;

import com.spikeflow.common.model.bean.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by 王玉涛
 * @Classname TestController
 * @Description 测试控制器
 * @Date 2025/6/8 15:35
 */
@Tag(name = "测试接口", description = "提供基础测试功能")
@RequestMapping("/test")
@Slf4j
@RequiredArgsConstructor
@RestController
public class TestController {

    /**
     * 测试问候接口
     * @param session HTTP会话对象
     * @return 包含问候信息和访问次数的结果
     */
    @Operation(summary = "问候接口", description = "返回问候信息和访问次数统计")
    @ApiResponse(responseCode = "200", description = "成功响应", content = @Content(schema = @Schema(implementation = Result.class)))
    @GetMapping("/hi")
    public Result<String> hi(HttpSession session) {
        Long visitCount = (Long) session.getAttribute("visitCount");
        if (visitCount == null) {
            visitCount = 1L;
        }
        session.setAttribute("visitCount", visitCount + 1);
        return Result.success("hello SpikeFlow, visitCount: " + visitCount);
    }
}
