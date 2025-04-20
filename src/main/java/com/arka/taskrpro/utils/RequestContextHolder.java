package com.arka.taskrpro.utils;

import com.arka.taskrpro.models.entity.Role;

public class RequestContextHolder {

    private static final ThreadLocal<String> tenantIdHolder = new ThreadLocal<>();
    private static final ThreadLocal<Long> userIdHolder = new ThreadLocal<>();
    private static final ThreadLocal<Role> roleHolder = new ThreadLocal<>();


    public static String getTenantId() {
        return tenantIdHolder.get();
    }

    public static void setTenantId(String tenantId) {
        tenantIdHolder.set(tenantId);
    }

    public static Long getUserId() {
        return userIdHolder.get();
    }

    public static void setUserId(Long userId) {
        userIdHolder.set(userId);
    }

    public static Role getRole() {
        return roleHolder.get();
    }

    public static void setRole(Role role){
        roleHolder.set(role);
    }

    public static void clear() {
        tenantIdHolder.remove();
        userIdHolder.remove();
        roleHolder.remove();
    }
}
