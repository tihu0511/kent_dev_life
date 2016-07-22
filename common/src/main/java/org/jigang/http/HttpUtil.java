package org.jigang.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * HTTP,HTTPS请求工具
 * Created by wujigang on 16/6/30.
 */
public class HttpUtil {
    public static final String PLAIN_TEXT = "text/plain";
    public static final String TEXT_XML = "text/xml";
    public static final String APPLICATION_XML = "application/xml";
    public final static String APPLICATION_JSON = "application/json";

    private static final int BUFFER_SIZE = 1024;

    private static final Logger logger = Logger.getLogger(HttpUtil.class);
    //默认编码
    private static final String DEFAULT_CHARSET = "UTF-8";

    // 超时时间(毫秒)
    private static Integer TIMEOUT = 30000;
    private static Integer MAX_REDIRECTS = 10;

    private static SSLConnectionSocketFactory socketFactory;

    /**
     * get请求
     * 支持HTTP,HTTPS
     * @param url
     * @return
     */
    public static String get(String url) throws IOException {
        return get(url, DEFAULT_CHARSET);
    }

    /**
     * get请求
     * 支持HTTP,HTTPS
     * @param url
     * @param charset
     * @return
     */
    public static String get(String url, String charset) throws IOException {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = getHttpClient();
            HttpGet httpGet = new HttpGet(url);
            logger.info("executing Get request " + httpGet.getURI());
            response = httpClient.execute(httpGet);
            return resolveResponse(response, charset);
        } finally {
            if (response != null) {
                response.close();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        }
    }

    /**
     * POST请求
     * @param url
     * @return
     * @throws IOException
     */
    public static String post(String url) throws IOException {
        return post(url, null, DEFAULT_CHARSET);
    }

    /**
     * POST请求
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String post(String url, Map<String, String> params) throws IOException {
        return post(url, params, DEFAULT_CHARSET);
    }

    /**
     * POST发送普通文本
     * @param url
     * @param content
     * @param charset
     * @return
     * @throws IOException
     */
    public static String postPlainText(String url, String content, String charset) throws IOException {
        return postContent(url, PLAIN_TEXT, content, charset);
    }

    /**
     * POST发送xml
     * @param url
     * @param xml
     * @param charset
     * @return
     * @throws IOException
     */
    public static String postTextXml(String url, String xml, String charset) throws IOException {
        return postContent(url, TEXT_XML, xml, charset);
    }

    /**
     * POST发送xml
     * @param url
     * @param xml
     * @param charset
     * @return
     * @throws IOException
     */
    public static String postApplicationXml(String url, String xml, String charset) throws IOException {
        return postContent(url, APPLICATION_XML, xml, charset);
    }

    /**
     * POST发送JSON
      * @param url
     * @param json
     * @param charset
     * @return
     */
    public static String postJson(String url, String json, String charset) throws IOException {
        return postContent(url, APPLICATION_JSON, json, charset);
    }

    /**
     * POST请求
     * @param url
     * @param params
     * @param charset
     * @return
     * @throws IOException
     */
    public static String post(String url, Map<String, String> params, String charset) throws IOException {

        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = getHttpClient();
        // 创建httppost
        HttpPost httpPost = new HttpPost(url);
        // 创建参数队列
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        if(params != null && params.size() > 0){
            for (String key : params.keySet()){
                formParams.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        CloseableHttpResponse response = null;
        try {
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formParams, charset);
            httpPost.setEntity(uefEntity);
            logger.info("executing POST request " + httpPost.getURI());
            response = httpclient.execute(httpPost);
            return resolveResponse(response, charset);
        } finally {
            if (null != response) {
                response.close();
            }
            if (null != httpclient) {
                httpclient.close();
            }
        }
    }

    /**
     * POST请求,指定请求ContentType
     * @param url
     * @param content
     * @param mimeType
     * @param charset
     * @return
     * @throws IOException
     */
    public static String postContent(String url, String mimeType, String content, String charset) throws IOException {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = getHttpClient();
            HttpPost httpPost = new HttpPost(url);
            StringEntity stringEntity = new StringEntity(content,charset);
            stringEntity.setContentType(mimeType);
            httpPost.setEntity(stringEntity);

            logger.info("executing POST request " + httpPost.getURI());
            response = httpClient.execute(httpPost);
            return resolveResponse(response, charset);
        } finally {
            if (null != response) {
                response.close();
            }
            if (null != httpClient) {
                httpClient.close();
            }
        }
    }

    /**
     * 解析返回结果
     * @param response
     * @param charset
     * @return
     * @throws IOException
     */
    private static String resolveResponse(CloseableHttpResponse response, String charset) throws IOException {
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = null;
        BufferedOutputStream bos = null;

        try {
            //跳转到重定向的url
            if (response.getStatusLine().getStatusCode() == 302) {
                String locationUrl = response.getLastHeader("Location").getValue();
                logger.info("重定向到url=" + locationUrl);
                return get(locationUrl, charset);
            }else {
                HttpEntity entity = response.getEntity();
                bis = new BufferedInputStream(entity.getContent());
                baos = new ByteArrayOutputStream();
                bos = new BufferedOutputStream(baos);

                byte[] buf = new byte[BUFFER_SIZE];
                int len = -1;
                while((len = bis.read(buf)) != -1) {
                    bos.write(buf, 0, len);
                }
                bos.flush();

                return new String(baos.toByteArray(), charset);
            }
        } finally {
            if (null != bos) {
                bos.close();
            }
            if (null != baos) {
                baos.close();
            }
            if (null != bis) {
                bis.close();
            }
        }
    }

    private static CloseableHttpClient getHttpClient(){
        enableSSL();
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.STANDARD_STRICT)
                .setExpectContinueEnabled(true)
                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
                .setSocketTimeout(TIMEOUT)
                .setConnectTimeout(TIMEOUT)
                .setMaxRedirects(MAX_REDIRECTS)
                .setRedirectsEnabled(true)
                .build();
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", socketFactory).build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();
        return httpClient;
    }


    private  static void  enableSSL(){
        try{
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null,new TrustManager[]{manager},null);
            socketFactory = new SSLConnectionSocketFactory(context, NoopHostnameVerifier.INSTANCE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static TrustManager manager = new X509TrustManager() {
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };

}
