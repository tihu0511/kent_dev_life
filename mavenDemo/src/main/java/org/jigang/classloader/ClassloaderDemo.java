package org.jigang.classloader;

import java.net.URL;

/**
 * Created by wujigang on 16/7/8.
 */
public class ClassloaderDemo {
    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("test.properties");
        System.out.println(url);

        Thread t = Thread.currentThread();
        ClassLoader cl2 = t.getContextClassLoader();
        URL url2 = cl2.getResource("test.properties");

        ClassLoader c1 = ClassLoader.getSystemClassLoader();
        URL u1 = c1.getResource("test.properties");
    }
}
