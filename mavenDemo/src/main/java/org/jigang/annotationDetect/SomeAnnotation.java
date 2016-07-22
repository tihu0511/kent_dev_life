package org.jigang.annotationDetect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by wujigang on 16/7/7.
 */
@Documented
@Retention(value=RUNTIME)
@Target(value= ElementType.TYPE)
public @interface SomeAnnotation {
}
