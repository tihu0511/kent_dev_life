package org.jigang.http;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by wujigang on 16/6/30.
 */
public class HttpTest {
    @Test
    public void get() throws IOException {
        String url = "https://www.baidu.com/";
        String result = HttpUtil.get(url);
        System.out.println(result);
    }

    @Test
    public void post() throws IOException {
        String url = "https://www.baidu.com/";
        String result = HttpUtil.post(url);
        System.out.println(result);
    }
}
