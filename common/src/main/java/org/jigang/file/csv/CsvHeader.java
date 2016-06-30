package org.jigang.file.csv;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 解析csv文件时使用
 * Created by BF100271 on 2016/6/23.
 */
@Documented
@Retention(value=RUNTIME)
@Target(value= ElementType.FIELD)
public @interface CsvHeader {
    String value();
}
