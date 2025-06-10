package com.spikeflow.stresstest;

import ch.qos.logback.core.util.StringUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spikeflow.common.util.JwtUtils;
import com.spikeflow.common.util.StringUtils;
import com.spikeflow.user.factory.UserFactory;
import com.spikeflow.user.mapper.UserMapper;
import com.spikeflow.user.model.bean.User;
import com.spikeflow.user.model.constant.UserConstant;
import com.spikeflow.user.model.request.UsersTokenRequest;
import com.spikeflow.user.service.IUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户压力测试类
 * 主要用于测试用户相关功能的性能和数据批量插入能力
 * 包含以下测试场景：
 * 1. 批量用户登录并生成JWT令牌测试
 * 2. 批量用户数据插入性能测试
 * 3. 单个用户数据插入测试
 *
 * @author 王玉涛
 * @version 1.0
 * @since 2025/6/10
 */
@SpringBootTest
@Slf4j
public class UserStressTest {

    @Resource
    private UserMapper userMapper; // 用户数据访问层接口，用于数据库CRUD操作
    
    @Resource
    private PasswordEncoder passwordEncoder; // Spring Security密码编码器，用于密码加密
    
    @Value("${mock.user.password}")
    private String password; // 测试用户默认密码，从application.yml配置文件中读取
    
    @Value("${mock.user.count}")
    private int count; // 批量测试时的用户数量，从application.yml配置文件中读取

    @Value("${mock.user.count}")
    private int mockUserCount; // 登录测试时的模拟用户数量

    @Resource
    private JwtUtils jwtUtils; // JWT工具类，用于生成和解析令牌

    @Resource
    private IUserService userService; // 用户服务接口

    private static final String USER_TOKEN_TEXT = "src/main/resources/static/UserIdAndJwtToken.txt"; // 用户ID和令牌存储文件路径

    private final String intervals = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"; // 日志分隔线

    /**
     * 测试用户登录并打印令牌信息
     * 1. 创建输出文件并写入CSV头
     * 2. 获取当前用户总数
     * 3. 随机选择测试用户ID
     * 4. 批量生成JWT令牌并写入文件
     *
     * @throws IOException 文件操作异常
     */
    @Test
    public void testLoginAndPrint() throws IOException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(USER_TOKEN_TEXT));
        bufferedOutputStream.flush();

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        Long userCount = userMapper.selectCount(userQueryWrapper);
        log.info("当前用户总数目: {}", userCount);
        System.out.println(intervals);

        log.info("随机生成userId.....");
        List<Long> counts = randomCounts(userCount);
        System.out.println(intervals);

        Map<Long, String> userIdAndJwtToken = loginAndCreateToken(counts);

        for (Map.Entry<Long, String> userIdTokenEntry : userIdAndJwtToken.entrySet()) {
            Long userId = userIdTokenEntry.getKey();
            String token = userIdTokenEntry.getValue();
            String line = userId + "," + token + "\n";
            bufferedOutputStream.write(line.getBytes());
            log.info("用户记录成功：{}", userId);
        }
        bufferedOutputStream.close();
    }

    /**
     * 测试批量插入用户
     * 1. 根据配置数量(count)批量创建随机用户
     * 2. 记录插入成功/失败的数量
     * 3. 计算并打印成功率
     */
    @Test
    public void testInsertCountUser() {
        if (count <= 0) {
            log.error("模拟插入用户数必须大于0");
            return;
        }

        int errorCount = 0; // 记录失败次数

        for (int i = 0; i < count; i++) {
            // 1. 创建随机用户
            User user = UserFactory.createRandomUser(password);
            // 2. 插入数据库
            int insert = userMapper.insert(user);

            if (insert <= 0) {
                errorCount++;
                continue;
            }

            log.info("模拟插入用户成功: {}", user);
        }

        log.info("测试结果 - 总数: {}, 成功: {}, 失败: {}, 成功率: {}%",
                count,
                count - errorCount,
                errorCount,
                (count - errorCount) * 100.0 / count);
    }

    /**
     * 测试插入单个随机用户
     * 1. 验证密码是否设置
     * 2. 对密码进行加密
     * 3. 创建随机用户并插入数据库
     */
    @Test
    public void testInsertRandomUser() {
        if (StringUtil.isNullOrEmpty(password)) {
            log.error("请设置密码");
            return;
        }

        // 密码加密
        String encode = passwordEncoder.encode(password);
        // 创建随机用户
        User randomUser = UserFactory.createRandomUser(encode);
        // 插入数据库
        int insert = userMapper.insert(randomUser);

        if (insert <= 0) {
            log.error("插入用户失败!");
            return;
        }
        log.info("插入成功: {}", randomUser);
    }

    /**
     * 生成不重复的随机用户ID集合
     * @param userCount 当前用户总数
     * @return 随机用户ID列表
     */
    private List<Long> randomCounts(Long userCount) {
        Set<Long> counts = new HashSet<>();
        for (int i = 0; i < mockUserCount; i++) {
            boolean add = counts.add(RandomUtil.randomLong(1, userCount));
            if (!add) {
                i--; // 重复则重试
            }
        }

        return new ArrayList<>(counts);
    }

    /**
     * 批量登录并生成JWT令牌
     * @param userCount 用户ID列表
     * @return 用户ID和令牌的映射关系
     */
    private Map<Long, String> loginAndCreateToken(List<Long> userCount) {
        log.info("开始批量登录并获取JwtToken: {}条", userCount);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.in("id", userCount);
        List<User> users = userMapper.selectList(userQueryWrapper);

        Map<Long, String> userTokenMap = new HashMap<>();

        for (User user : users) {
            String jwt = jwtUtils.generateToken(user);
            Long userId = user.getId();
            userTokenMap.put(userId, jwt);

            log.info("用户: {} 记录成功", user.getUsername());
        }

        System.out.println(intervals);

        return userTokenMap;
    }
}
