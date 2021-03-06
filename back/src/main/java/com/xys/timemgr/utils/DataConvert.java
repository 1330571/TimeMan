package com.xys.timemgr.utils;

import com.xys.timemgr.provider.StringAble;

import java.util.*;

/**
 * @author xys
 * @version 0.0
 * @since 2020-12
 */

public class DataConvert {
    /**
     * 将字符串数组拼接成用_连接的单一字段
     *
     * @param source 数据源
     * @return 拼接字符串
     */
    public static String concatString(StringAble source) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : source.getStringArrays()) {
            stringBuilder.append(str);
            stringBuilder.append('_');
        }
        String temp = stringBuilder.toString();
        temp = temp.substring(0, temp.length() - 1);
        return temp;
    }

    public static String concatString(String[] source) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : source) {
            stringBuilder.append(str);
            stringBuilder.append('_');
        }
        String temp = stringBuilder.toString();
        temp = temp.substring(0, temp.length() - 1);
        return temp;
    }

    public static String concatString(List<String> source) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : source) {
            stringBuilder.append(str);
            stringBuilder.append('_');
        }
        String temp = stringBuilder.toString();
        temp = temp.substring(0, temp.length() - 1);
        return temp;
    }

    /**
     * 拆分拼接的字符串
     *
     * @param str 拼接字符串
     * @return 拆分的字符串数组
     */
    public static String[] splitString(String str) {
        if (str == null) return null;
        return str.split("_");
    }

    /**
     * 拆分拼接的字符串2
     *
     * @param str 拼接起来的字符串
     * @return 拆分成List的字符串数组
     */
    public static List<String> splitStringList(String str) {
        String[] arr = str.split("_");
        return new ArrayList<>(Arrays.asList(arr));
    }

    /**
     * 返回一个字符串解析的属性名-属性对
     *
     * @param str 记录属性的字符串吧
     * @return 记载着键值对的Map数据结构
     */
    public static Map<String, String> getStringPair(String str) {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        String[] splitStr = splitString(str);
        assert (splitStr.length % 2 == 0);
        for (int i = 0; i < splitStr.length; i += 2) {
            stringStringHashMap.put(splitStr[i], splitStr[i + 1]);
        }
        return stringStringHashMap;
    }
}
