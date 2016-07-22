package org.jigang.collection;

import java.util.Collection;

/**
 * Created by wujigang on 16/7/11.
 */
public class CollectionUtil {
    public static boolean isEmpty(Collection c) {
        return null == c || c.size() <= 0;
    }

    public static boolean isNotEmpty(Collection c) {
        return !isEmpty(c);
    }
}
