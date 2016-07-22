package org.jigang.generator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by wujigang on 16/7/15.
 */
public class PropertiesHelper {
    private static final String GENERATOR_CONFIG = "mybatisGenerator.properties";
    private static Properties props;

    static {
        props = new Properties();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(GENERATOR_CONFIG);
        try {
            props.load(is);
        } catch (IOException e) {
            System.out.println("载入mybatisGenerator.properties配置失败!!!");
            e.printStackTrace();

        }
    }

    public static String getProps(String name) {
        return props.getProperty(name);
    }
}
