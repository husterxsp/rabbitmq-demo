package com.example.demo.util;

import com.alibaba.fastjson.JSON;

/**
 * @author xushaopeng
 * @date 2018/12/15
 */
public class BeanStringConvert {
    public static <T> String bean2String(T value) {
        if (value == null) {
            return null;
        }

        Class<?> clazz = value.getClass();

        // ??? 为什么需要int 和 Integer?
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return value.toString();
            // long.class 代表什么？Long.class?
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        }

        return JSON.toJSONString(value);
    }

    public static <T> T string2Bean(String str, Class<T> clazz) {

        if (str == null || str.length() <= 0) {
            return null;
        }

        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        }

        return JSON.toJavaObject(JSON.parseObject(str), clazz);
    }
}
