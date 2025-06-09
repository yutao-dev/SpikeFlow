package com.spikeflow.user.model.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户角色关联实体类
 *
 * @author 王玉涛
 * @version 1.0
 * @since 2025/6/9
 */
@Data
@TableName("user_role")
public class UserRole {
    /**
     * 主键ID，自增
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 创建时间，自动填充
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
}
