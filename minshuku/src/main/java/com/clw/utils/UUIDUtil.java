package com.clw.utils;

import java.util.UUID;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/5/5 15:11
 */
public class UUIDUtil {
    /**
     * 去除UUID的"-"
     * @return
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("\\-", "");
    }
}
