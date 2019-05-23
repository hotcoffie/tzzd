package com.ttit.tzzd.sys.utils;

import java.util.UUID;

/**
 * Description:
 *
 * @author 小谢
 * Date: 2019/5/239:45
 */
public class UuidUtils {
    public static String generate(){
        return UUID.randomUUID().toString().replaceAll("\\W", "");
    }
}
