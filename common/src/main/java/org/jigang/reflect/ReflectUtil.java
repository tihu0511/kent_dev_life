package org.jigang.reflect;

import org.apache.log4j.Logger;
import org.jigang.date.DateUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;

/**
 * Created by BF100271 on 2016/6/23.
 */
public class ReflectUtil {
    private static final Logger logger = Logger.getLogger(ReflectUtil.class);

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 利用反射机制设置成员变量值
     * 仅支持基本包装类型、String、Date，其中Date默认格式为yyyy-MM-dd HH:mm:ss
     * @param field
     * @param instance
     * @param value
     * @param <T>
     * @throws IllegalAccessException
     */
    public static <T> void setFieldValue(Field field, Object instance, String value) throws IllegalAccessException, ParseException {
        Type type = field.getGenericType();
        String typeStr = type.toString();

        if (typeStr.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
            field.set(instance, value);
        } else if (typeStr.equals("class java.lang.Integer")) {
            field.set(instance, Integer.valueOf(value));
        } else if (typeStr.equals("class java.lang.Boolean")) {
            field.set(instance, Boolean.valueOf(value));
        } else if (typeStr.equals("class java.math.BigDecimal")) {
            field.set(instance, new BigDecimal(value));
        } else if (typeStr.equals("class java.util.Date")) {
            field.set(instance, DateUtil.parse(value, DATE_FORMAT));
        }
    }

}
