package com.arka.taskrpro.utils;
public class RequestContextHolder {

    private static final ThreadLocal<String> tenantIdHolder = new ThreadLocal<>();
    private static final ThreadLocal<Long> userIdHolder = new ThreadLocal<>();

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

    public static void clear() {
        tenantIdHolder.remove();
        userIdHolder.remove();
    }
}
