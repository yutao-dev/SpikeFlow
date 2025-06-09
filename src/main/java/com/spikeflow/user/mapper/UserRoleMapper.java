package com.spikeflow.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spikeflow.user.model.bean.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色关系Mapper接口
 * 
 * @author 王玉涛
 * @InterfaceName UserRoleMapper
 * @Description 操作用户角色关系数据的Mapper接口，继承MyBatis-Plus的BaseMapper提供基础CRUD操作
 * @Date 2025/6/9 21:22
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
}
