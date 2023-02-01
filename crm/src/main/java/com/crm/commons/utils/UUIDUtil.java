package com.crm.commons.utils;

import java.util.UUID;

/**
 * Author: zr
 * Date: 2023/1/1 11:20
 * Description:
 */
public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
