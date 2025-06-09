package com.spikeflow.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spikeflow.user.model.bean.User;
import com.spikeflow.user.model.request.UsersTokenRequest;
import com.spikeflow.user.model.response.UsersMeResponse;

/**
 * @author by 王玉涛
 * @InterfaceName IUserService
 * @Description TODO
 * @Date 2025/6/9 21:22
 */
public interface IUserService extends IService<User> {

    /**
     * 用户登录
     * @param request
     * @return
     */
    String login(UsersTokenRequest request);

    /**
     * 获取当前用户信息
     * @return
     */
    UsersMeResponse me();
}
