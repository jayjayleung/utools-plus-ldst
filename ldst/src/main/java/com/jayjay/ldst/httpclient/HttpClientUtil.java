package com.jayjay.ldst.httpclient;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Copyright © 2020-ESS
 *
 * @Project: MRC
 * @Author: JayJay
 * @Date: 2020/10/30
 * @ClassName: HttpClientUtil
 * @Description:
 */
public class HttpClientUtil {

    public static String get(String url) throws IOException {
        //建立一个新的请求客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //使用HttpGet的方式请求网址
        HttpGet httpGet = new HttpGet(url);

        setConfig(httpGet);
        //获取网址的返回结果
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获取返回结果中的实体
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        response.close();
        httpClient.close();
        return result;
    }

    public static String getImg(String url) throws IOException {
        //建立一个新的请求客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        setConfig(httpGet);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        String value = response.getEntity().getContentType().getValue().split("/")[1];
        System.out.println(value);
        File file = new File("D:/jayjay/jsoupimg/vvv/"+"xxx."+value);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        response.getEntity().writeTo(fileOutputStream);
        fileOutputStream.close();
        response.close();
        httpClient.close();
        return "";
    }

    public static void setConfig(HttpGet httpGet){
        httpGet.setHeader("Referer", "https://www.mzitu.com/");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36");
        httpGet.setConfig(RequestConfig.custom()
                .setConnectionRequestTimeout(10000)
                .setConnectTimeout(10000)
                .setSocketTimeout(10000).build());
    }
}
