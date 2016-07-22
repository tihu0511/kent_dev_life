package org.jigang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URL;

/**
 * Created by BF100271 on 2016/5/30.
 */
@Controller
public class TestController {
    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        ClassLoader c1 = ClassLoader.getSystemClassLoader();
        URL u = c1.getResource("");

        URL url = ClassLoader.getSystemResource("db.properties");
        ClassLoader cl = this.getClass().getClassLoader();
        URL url1 = cl.getResource("db.properties");
        System.out.println(url);
        ClassLoader cl2 = Thread.currentThread().getContextClassLoader();
        URL url2 = cl2.getResource("db.properties");
        return url.getPath();
    }
}
