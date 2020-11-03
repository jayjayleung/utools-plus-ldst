package com.jayjay.ldst.util;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
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
import java.util.UUID;

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

        setConfig2Html(httpGet);
        //获取网址的返回结果
        CloseableHttpResponse response = null;
        try {
                response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
//            HttpClientUtil.get(url);
        }
        String result = "";
        if(response.getStatusLine().getStatusCode() == 200) {
            //获取返回结果中的实体
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity,"UTF-8");
        }
        response.close();
        httpClient.close();
        return result;
    }

    public static String getImg(String url) throws IOException {
        //建立一个新的请求客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        setConfig2Img(httpGet);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        String value = response.getEntity().getContentType().getValue().split("/")[1];
//        System.out.println(value);
        String uuid = UUID.randomUUID().toString();
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

    public static String getImg(String url,String path) throws IOException {
        //建立一个新的请求客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        setConfig2Img(httpGet);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        String value = response.getEntity().getContentType().getValue().split("/")[1];
//        System.out.println(value);
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid+"."+value;
        File file = new File(path+fileName);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        response.getEntity().writeTo(fileOutputStream);
        fileOutputStream.close();
        response.close();
        httpClient.close();
        return fileName;
    }

    public static void setConfig2Html(HttpGet httpGet){
        httpGet.setHeader("Referer", "http://www.win4000.com/meitu.html");
        httpGet.setHeader("Upgrade-Insecure-Requests", "1");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4270.0 Safari/537.36");

//        HttpHost httoHost = new HttpHost("14.20.235.107",9797);
//        HttpHost httoHost = new HttpHost("123.55.102.36",9999);
//        HttpHost httoHost = new HttpHost("180.160.55.97",37805);
//        HttpHost httoHost = new HttpHost("61.163.32.88",3128);
        httpGet.setConfig(RequestConfig.custom()
                .setConnectionRequestTimeout(5000)
                .setConnectTimeout(10000)
                .setSocketTimeout(10000)
//                .setProxy(httoHost)
                .build());

    }

    public static void setConfig2Img(HttpGet httpGet){
        httpGet.setHeader("Referer", "http://www.win4000.com");
        httpGet.setHeader("Upgrade-Insecure-Requests", "1");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4270.0 Safari/537.36");

//        HttpHost httoHost = new HttpHost("14.20.235.107",9797);
//        HttpHost httoHost = new HttpHost("123.55.102.36",9999);
//        HttpHost httoHost = new HttpHost("180.160.55.97",37805);
//        HttpHost httoHost = new HttpHost("61.163.32.88",3128);
        httpGet.setConfig(RequestConfig.custom()
                .setConnectionRequestTimeout(5000)
                .setConnectTimeout(10000)
                .setSocketTimeout(10000)
//                .setProxy(httoHost)
                .build());

    }
}
