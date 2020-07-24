package com.cl.wyn.core.util.common;

/**
 * by cl at 2020/5/18 0018
 */
public class StringUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean notEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     *  去除标记
     * @param val
     * @param tags
     * @return
     */
    public static String removeTags(String val, String[] tags) {
        String newVal = new String(val);
        for(String tag : tags) {
            newVal = newVal.replace(tag, "");
        }
        return newVal;
    }
}
