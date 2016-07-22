package org.jigang.http;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by wujigang on 16/7/3.
 */
public class HttpTest2 {
    @Test
    public void testPostXml() throws IOException {
        String url = "http://localhost:8082/xml/v2/kent";
        String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><id>1</id><name>abc</name><money>100.11</money><day>2016-07-03 01:01:01</day></root>";
//        String result = HttpUtil.postApplicationXml(url, xml, "GBK");
        String result = HttpUtil.postContent(url, "application/xml;charset=gbk", xml, "gbk");
        System.out.println(result);
    }


    @Test
    public void testPostXml2() throws IOException {
        String url = "http://localhost:8082/xml/v1";
        String xml = "<xml><id>1</id><name>abc</name><money>100.11</money><day>2016-07-03 01:01:01</day></xml>";
        String result = HttpUtil.postApplicationXml(url, xml, "UTF-8");
        System.out.println(result);
    }
}
