package com.spikeflow.util;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.spikeflow.user.mapper.UserMapper;
import com.spikeflow.user.model.bean.User;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码编码器测试类
 * 
 * @author 王玉涛
 * @ClassName PasswordEncoderTest
 * @Description 测试Spring Security的密码编码功能，包括密码加密和数据库更新
 * @Date 2025/6/9 21:55
 */
@SpringBootTest
public class PasswordEncoderTest {

    /**
     * Spring Security密码编码器，用于加密密码
     */
    @Resource
    private PasswordEncoder passwordEncoder;
    
    /**
     * 用户数据访问接口，用于操作用户表
     */
    @Resource
    private UserMapper userMapper;

    /**
     * 测试密码编码功能
     * 1. 使用PasswordEncoder对明文密码"123456"进行加密
     * 2. 打印加密后的密码字符串
     * 3. 使用UpdateWrapper更新数据库中的密码字段
     * 4. 打印被修改的行数
     */
    @Test
    public void testPasswordEncoder() {
        String encode = passwordEncoder.encode("123456");
        System.out.println("加密后的密码：" + encode);

        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper = userUpdateWrapper.set("password", encode);
        int update = userMapper.update(userUpdateWrapper);

        System.out.println("修改行数: " + update);
    }
}
