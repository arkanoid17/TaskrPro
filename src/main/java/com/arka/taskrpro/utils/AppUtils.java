package com.arka.taskrpro.utils;

import com.arka.taskrpro.models.entity.Role;

public class AppUtils {

    public static String createTenantIdFromName(String name){
        return name.trim().toLowerCase().replaceAll("\\.","").replaceAll(" ","-");
    }
}
