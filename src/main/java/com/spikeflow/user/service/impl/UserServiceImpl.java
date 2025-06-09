package com.spikeflow.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spikeflow.common.exception.UserException;
import com.spikeflow.common.model.enums.UserCode;
import com.spikeflow.common.util.JwtUtils;
import com.spikeflow.common.util.SecurityUtils;
import com.spikeflow.user.factory.UserFactory;
import com.spikeflow.user.mapper.UserMapper;
import com.spikeflow.user.model.bean.User;
import com.spikeflow.user.model.request.UsersTokenRequest;
import com.spikeflow.user.model.response.UsersMeResponse;
import com.spikeflow.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author by 王玉涛
 * @Classname UserServiceImpl
 * @Description TODO
 * @Date 2025/6/9 21:22
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    /**
     * 用户登录
     * @param request
     * @return
     */
    @Override
    public String login(UsersTokenRequest request) {
        // 1. 封装认证对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        // 2. 手动认证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 3. 获取对象
        User user = (User) authenticate.getPrincipal();
        // 4. 封装jwt并返回
        return jwtUtils.generateToken(user);
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    @Override
    public UsersMeResponse me() {
        User user = SecurityUtils.getUser();
        if (user == null) {
            throw new UserException(UserCode.USER_NOT_EXIST);
        }
        return UserFactory.createMeResponse(user);
    }
}
