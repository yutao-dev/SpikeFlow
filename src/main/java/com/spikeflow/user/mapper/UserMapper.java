package com.spikeflow.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spikeflow.user.model.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户数据访问层接口
 * 
 * @author 王玉涛
 * @InterfaceName UserMapper
 * @Description 基于MyBatis-Plus的用户数据访问层接口，提供对用户表的CRUD操作
 * @Date 2025/6/9 21:21
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
