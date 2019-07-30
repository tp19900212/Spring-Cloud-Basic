package com.quyc.common.base.utils;

import com.alibaba.fastjson.JSONObject;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static final String NULL = "null";

    public static boolean isBlank(final CharSequence cs) {
        if (NULL.equals(org.apache.commons.lang3.StringUtils.trim(String.valueOf(cs)))) {
            return true;
        }
        return org.apache.commons.lang3.StringUtils.isBlank(cs);
    }

    public static boolean isNotBlank(final CharSequence cs) {
        if (NULL.equals(org.apache.commons.lang3.StringUtils.trim(String.valueOf(cs)))) {
            return false;
        }

        return org.apache.commons.lang3.StringUtils.isNotBlank(cs);
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        if (NULL.equals(org.apache.commons.lang3.StringUtils.trim(String.valueOf(cs)))) {
            return false;
        }
        return org.apache.commons.lang3.StringUtils.isNotEmpty(cs);
    }

    /**
     * getPageJSONObject:(当前页转化为MYSQL所需要的分页参数)
     *
     * @param currentCount
     * @param pageSize
     * @return
     * @Author airufei
     */

    public static JSONObject getPageJSONObject(int currentCount, int pageSize) {
        JSONObject map = new JSONObject();
        if (pageSize < 1) {
            pageSize = 10;
        }
        if (currentCount < 1) {
            currentCount = 1;
        }
        int startIndex = 0;
        if (currentCount > 1) {
            startIndex = (currentCount - 1) * pageSize;
        }
        map.put("startIndex", startIndex);
        map.put("pageSize", pageSize);
        map.put("currentCount", currentCount);
        map.put("sortWay", "desc");
        map.put("flag", 1);
        return map;
    }

}