package com.spikeflow.stresstest;

import com.spikeflow.user.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author by 王玉涛
 * @Classname StressTest
 * @Description TODO
 * @Date 2025/6/10 20:27
 */
@SpringBootTest
public class StressTest {

    @Resource
    private UserMapper userMapper;

}
