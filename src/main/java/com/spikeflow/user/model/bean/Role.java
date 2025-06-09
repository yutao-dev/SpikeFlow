package com.spikeflow.user.model.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 角色实体类
 *
 * @author 王玉涛
 * @version 1.0
 * @since 2025/6/9
 */
@Data
@TableName("roles")
public class Role {
    /** 
     * 角色ID 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 
     * 角色名称 
     */
    @TableField("role_name")
    private String roleName;

    /** 
     * 角色编码 
     */
    @TableField("role_code")
    private String roleCode;

    /** 
     * 角色描述 
     */
    private String description;

    /** 
     * 状态: 0-禁用, 1-正常 
     */
    private Integer status;

    /** 
     * 创建时间 
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /** 
     * 更新时间 
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
