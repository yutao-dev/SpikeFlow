package com.spikeflow.user.model.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * 用户实体类
 * 
 * @author 王玉涛
 * @version 1.0
 * @since 2025/6/9
 */
@Data
@TableName("users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 密码
     */
    @JsonIgnore
    private String password;
    
    /**
     * 邮箱
     */
    @JsonIgnore
    private String email;
    
    /**
     * 手机号
     */
    @JsonIgnore
    private String phone;
    
    /**
     * 头像URL
     */
    private String avatar;
    
    /**
     * 用户状态(0-禁用,1-正常)
     */
    @JsonIgnore
    private Integer status;

    /**
     * 最后登录时间
     */
    @TableField("last_login")
    private LocalDateTime lastLogin;

    /**
     * 角色列表
     */
    @TableField(exist = false)
    private List<Role> roles;

    /**
     * 创建时间
     */
    @JsonIgnore
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @JsonIgnore
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
