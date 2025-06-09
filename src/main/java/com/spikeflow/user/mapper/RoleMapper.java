package com.spikeflow.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spikeflow.user.model.bean.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色数据访问接口
 * 
 * @author 王玉涛
 * @InterfaceName RoleMapper
 * @Description 角色管理的Mapper接口，继承MyBatis-Plus的BaseMapper提供基本CRUD操作
 * @Date 2025/6/9 21:21
 * @see com.baomidou.mybatisplus.core.mapper.BaseMapper
 * @see com.spikeflow.user.model.bean.Role
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
