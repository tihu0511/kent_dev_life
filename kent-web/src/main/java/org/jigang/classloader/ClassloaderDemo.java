package org.jigang.classloader;

import java.net.URL;

/**
 * Created by wujigang on 16/7/8.
 */
public class ClassloaderDemo {
    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("test.properties");
        System.out.println(url);
    }
}
