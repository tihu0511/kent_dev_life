package org.jigang.annotationDetect;

import org.reflections.Reflections;

import java.util.Set;

/**
 * Created by wujigang on 16/7/7.
 */
public class AnnotationDetectTest {


    public static void main(String[] args) {
        Reflections reflections = new Reflections("org.jigang");

        //scan all subtype of SomeType
        Set<Class<? extends SomeType>> subTypes =
                reflections.getSubTypesOf(SomeType.class);

        for (Class clazz : subTypes) {
            System.out.println(clazz.getCanonicalName());
        }

        System.out.println();
        System.out.println();
        System.out.println();

        Set<Class<?>> annotated =
                reflections.getTypesAnnotatedWith(SomeAnnotation.class);
        for (Class clazz : annotated) {
            System.out.println(clazz.getCanonicalName());
        }
    }
}
