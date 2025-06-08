package com.spikeflow.common.test;

import com.spikeflow.common.model.bean.Result;
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
 * @Description TODO
 * @Date 2025/6/8 15:35
 */
@RequestMapping("/test")
@Slf4j
@RequiredArgsConstructor
@RestController
public class TestController {

    /**
     * 测试接口
     * @return
     */
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
