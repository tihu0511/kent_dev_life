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

    @Test
    public void postXml() throws IOException {
        String username = "kent";
        String url = "http://localhost:8080/services/v1/" + username + "/method=test1";
        String xml = "<Root><inId>11</inId><inName>kent</inName><inDay>2016-07-01 01:03:05</inDay><inMoney>123.45</inMoney></Root>";

        String result = HttpUtil.postTextXml(url, xml, "UTF-8");
        System.out.println(result);
    }

    @Test
    public void postJsonRest() throws IOException {
        String username = "kent";
        String url = "http://localhost:8080/services/v2/" + username + "/method=test";
        String json = "{\"query\":\"123\"}";

        String result = HttpUtil.postJson(url, json, "UTF-8");
        System.out.println(result);
    }

    @Test
    public void postPlainRest() throws IOException {
        String username = "kent";
        String url = "http://localhost:8080/services/test/post";

        String result = HttpUtil.postPlainText(url, "aa", "UTF-8");
        System.out.println(result);
    }

    @Test
    public void getRest() throws IOException {
        String username = "kent";
        String url = "http://localhost:8080/services/test";
        String xml = "<xml>aa</xml>";

        String result = HttpUtil.get(url);
        System.out.println(result);
    }

    @Test
    public void getJsonRest() throws IOException {
        String username = "kent";
        String url = "http://localhost:8080/services/test/json/" + username;

        String result = HttpUtil.get(url);
        System.out.println(result);
    }
}
