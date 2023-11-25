package com.kh.chatgpt.framework.assistant.core.util;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * httpclient工具类
 */
@Slf4j
public class HttpClientUtil {

    private static final int MAX_TIMEOUT = 50000;

    /**
     * 聚合http请求
     *
     * @param url 请求地址
     * @param headers 请求头
     * @param jsonParam 请求体
     * @param method 请求方式
     * @return 请求结果
     */
    public static String doJson(String url, Map<String, String> headers, String jsonParam, String method) {
        CloseableHttpClient httpclient = getHttpClient(null);
        CloseableHttpResponse response = null;
        String jsonStr = null;
        try {
            method = StrUtil.isEmpty(method) ? "" : method.toLowerCase();
            StringEntity entity = new StringEntity(jsonParam, ContentType.APPLICATION_JSON);
            switch (method) {
                case "get" -> {
                    HttpGet httpGet = new HttpGet(url);
                    setHeader(headers, httpGet);
                    response = httpclient.execute(httpGet);
                }
                case "post" -> {
                    HttpPost httpPost = new HttpPost(url);
                    setHeader(headers, httpPost);
                    httpPost.setEntity(entity);
                    response = httpclient.execute(httpPost);
                }
                case "put" -> {
                    HttpPut httpPut = new HttpPut(url);
                    setHeader(headers, httpPut);
                    httpPut.setEntity(entity);
                    response = httpclient.execute(httpPut);
                }
                case "delete" -> {
                    HttpDelete httpDelete = new HttpDelete(url);
                    setHeader(headers, httpDelete);
                    response = httpclient.execute(httpDelete);
                }
                default -> throw new RuntimeException("unsupported method");
            }
            jsonStr = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("unsupported method");
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    log.error("发送http请求发生错误：{}-》{}",e.getMessage(), e);
                }
            }
        }
        return jsonStr;
    }


    public static CloseableHttpClient getHttpClient(Integer time) {
        try {
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext ctx = SSLContext.getInstance(SSLConnectionSocketFactory.SSL);
            ctx.init(null, tm, new java.security.SecureRandom());
            System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(ctx, NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", socketFactory).build();
            // 创建ConnectionManager，添加Connection配置信息
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            connectionManager.setMaxTotal(200);
            connectionManager.setDefaultMaxPerRoute(20);
            int maxTime = time == null ? MAX_TIMEOUT : time;
            connectionManager.setValidateAfterInactivity(maxTime);
            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(maxTime).setConnectTimeout(maxTime).setSocketTimeout(maxTime).build();
            HttpRequestRetryHandler myRetryHandler = (exception, executionCount, context) -> {
                if (executionCount > 3) {
                    return false;
                }
                if (exception instanceof InterruptedIOException) {
                    return false;
                }
                if (exception instanceof UnknownHostException) {
                    return false;
                }
                if (exception instanceof SSLException) {
                    return false;
                }
                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                return !(request instanceof HttpEntityEnclosingRequest);
            };
            return HttpClientBuilder.create().setRetryHandler(myRetryHandler).setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig).build();
        } catch (KeyManagementException | NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void setHeader(Map<String, String> headers, HttpUriRequest httpPost) {
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                Header header = new BasicHeader(entry.getKey(), entry.getValue());
                httpPost.setHeader(header);
            }
        }
    }

}