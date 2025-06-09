package com.spikeflow.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spikeflow.common.exception.UserException;
import com.spikeflow.common.model.enums.UserCode;
import com.spikeflow.user.mapper.RoleMapper;
import com.spikeflow.user.mapper.UserMapper;
import com.spikeflow.user.mapper.UserRoleMapper;
import com.spikeflow.user.model.bean.Role;
import com.spikeflow.user.model.bean.User;
import com.spikeflow.user.model.bean.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户详情服务实现类
 * 
 * @author 王玉涛
 * @version 1.0
 * @since 2025/6/9
 * @Description 实现Spring Security的UserDetailsService接口，用于根据用户名加载用户详情信息
 */
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    /**
     * 用户数据访问接口
     */
    private final UserMapper userMapper;
    /**
     * 用户角色关系数据访问接口
     */
    private final UserRoleMapper userRoleMapper;
    /**
     * 角色数据访问接口
     */
    private final RoleMapper roleMapper;
    
    /**
     * 根据用户名加载用户详情
     * @param username 用户名
     * @return UserDetails 用户详情信息
     * @throws UsernameNotFoundException 当用户不存在时抛出此异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. 根据用户名查询用户基本信息
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper = userQueryWrapper.eq("username", username);
        User user = userMapper.selectOne(userQueryWrapper);
        if (user == null) {
            throw new UserException(UserCode.USER_NOT_EXIST);
        }

        // 2. 查询用户角色关联关系
        Long userId = user.getId();
        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper = userRoleQueryWrapper.eq("user_id", userId);
        List<UserRole> userRoles = userRoleMapper.selectList(userRoleQueryWrapper);

        // 3. 处理可能为空的角色关联
        userRoles = CollectionUtil.emptyIfNull(userRoles);
        
        // 4. 获取角色ID列表并查询角色详细信息
        List<Long> userRoleIds = userRoles.stream().map(UserRole::getRoleId).toList();
        List<Role> roles = roleMapper.selectBatchIds(userRoleIds);

        // 5. 设置用户角色信息并返回
        user.setRoles(roles);
        return user;
    }
}
