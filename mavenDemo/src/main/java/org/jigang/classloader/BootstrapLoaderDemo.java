package org.jigang.classloader;

import java.net.URL;

/**
 * Created by wujigang on 16/7/8.
 */
public class BootstrapLoaderDemo {
    public static void main(String[] args) {
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }

        System.out.println(System.getProperty("sun.boot.class.path"));
    }
}
