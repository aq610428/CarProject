package com.car.notver.util;

import java.util.List;
import java.util.Set;

/*字符串工具类*/
public class Utility {

    private Utility() {
    }


    public static boolean isNotEmpty(Object obj) {
        if (obj instanceof String) {
            return !isEmpty((String) obj);
        } else if (obj instanceof Long) {
            return !isEmpty((Long) obj);
        } else if (obj instanceof Integer) {
            return !isEmpty((Integer) obj);
        } else if (obj instanceof StringBuffer) {
            return !isEmpty((StringBuffer) obj);
        } else if (obj instanceof List) {
            return !isEmpty((List) obj);
        } else if (obj instanceof Set) {
            return !isEmpty((Set) obj);
        } else if (obj instanceof String[]) {
            return !isEmpty((String[]) obj);
        } else if (obj instanceof Object[]) {
            return !isEmpty((Object[]) obj);
        } else {
            return !isEmpty(obj);
        }
    }

    public static boolean equal(String s, String t) {
        if (s == null && t == null)
            return true;
        if (s != null && t != null)
            return s.trim().equals(t.trim());
        return s != null && s.equals(t);
    }

    public static boolean equal(Integer s, Integer t) {
        if (s == null && t == null)
            return true;
        return s != null && s.equals(t);
    }

    public static boolean equal(Long s, Long t) {
        if (s == null && t == null)
            return true;
        return s != null && s.equals(t);
    }


    public static boolean isEmpty(Object obj) {
        if (obj instanceof String) {
            return isEmpty((String) obj);
        } else if (obj instanceof Long) {
            return isEmpty((Long) obj);
        } else if (obj instanceof Integer) {
            return isEmpty((Integer) obj);
        } else if (obj instanceof StringBuffer) {
            return isEmpty((StringBuffer) obj);
        } else if (obj instanceof List) {
            return isEmpty((List) obj);
        } else if (obj instanceof Set) {
            return isEmpty((Set) obj);
        } else if (obj instanceof String[]) {
            return isEmpty((String[]) obj);
        } else if (obj instanceof Object[]) {
            return isEmpty((Object[]) obj);
        } else {
            return obj == null;
        }
    }


    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0 || str.trim().equals("") || str.equals("null"));
    }

    public static boolean isEmpty(Long lng) {
        return (lng == null);
    }

    public static boolean isEmpty(Integer inte) {
        return (inte == null);
    }


    public static boolean isEmpty(StringBuffer stringBuffer) {
        return (stringBuffer == null || stringBuffer.length() == 0 || stringBuffer
                .toString().trim().equals(""));
    }

    public static boolean isEmpty(Object[] array) {
        return (array == null || array.length == 0);
    }

    public static boolean isEmpty(String[] array) {
        return (array == null || array.length == 0);
    }


    public static boolean isEmpty(List list) {

        return (list == null || list.isEmpty() || list.get(0) == null);
    }


    public static boolean isEmpty(Set set) {
        return (set == null || set.isEmpty());
    }

}
