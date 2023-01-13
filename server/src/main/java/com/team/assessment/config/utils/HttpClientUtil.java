package com.team.assessment.config.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    final static int TIMEOUT = 1000;

    final static int TIMEOUT_MSEC = 5 * 1000;

    public static String doPost(String url, Map<String, String> paramMap) throws IOException {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";

        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);

            // 创建参数列表
            if (paramMap != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (Map.Entry<String, String> param : paramMap.entrySet()) {
                    paramList.add(new BasicNameValuePair(param.getKey(), param.getValue()));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }

            httpPost.setConfig(builderRequestConfig());

            // 执行http请求
            response = httpClient.execute(httpPost);

            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                throw e;
            }
        }

        return resultString;
    }

    public static String doGet(String url) throws IOException {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";

        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);

            httpPost.setConfig(builderRequestConfig());

            // 执行http请求
            response = httpClient.execute(httpPost);

            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                throw e;
            }
        }

        return resultString;
    }

    private static RequestConfig builderRequestConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(TIMEOUT_MSEC)
                .setConnectionRequestTimeout(TIMEOUT_MSEC)
                .setSocketTimeout(TIMEOUT_MSEC).build();
    }


    /**
     * 参数以raw的方式做post请求
     *
     * @param url
     * @param stringJson
     * @param headers
     * @param encode
     * @return
     */
    public static String httpPostRaw(String url, String stringJson, Map<String, String> headers, String encode) {
        String str = "";
        if (encode == null) {
            encode = "utf-8";
        }
        // HttpClients.createDefault()等价于 HttpClientBuilder.create().build();
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httpost = new HttpPost(url);

        // 设置header
        httpost.setHeader("Content-type", "application/json");
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        // 组织请求参数
        StringEntity stringEntity = new StringEntity(stringJson, encode);
        httpost.setEntity(stringEntity);
        String content = null;
        CloseableHttpResponse httpResponse = null;
        try {
            // 响应信息
            httpResponse = closeableHttpClient.execute(httpost);
            HttpEntity entity = httpResponse.getEntity();
            content = EntityUtils.toString(entity, encode);
            System.out.println(content);
            str = content;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try { // 关闭连接、释放资源
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}

