package com.xiaoxiong.nbst01.utils;

import java.util.Collection;
import java.util.Objects;

/**
 * @author: Ike.Fan
 * @date: 2018/4/2.
 */
public class EmptyChecker {
    public static boolean isEmpty(Collection collection) {
        if (collection == null || collection.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String string) {
        if (string == null || string.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(Objects[] objs) {
        if (objs == null || objs.length == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(int[] objs) {
        if (objs == null || objs.length == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Object obj) {
        return obj == null;
    }
}
