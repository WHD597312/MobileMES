package com.supcon.whd.common.utils;

public class InstanceUtils {
    private InstanceUtils(){}

    public static <T> T  getInstance(Class<T> clazz){
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
