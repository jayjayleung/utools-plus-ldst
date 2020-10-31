package com.jayjay.ldst.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Copyright © 2020-ESS
 *
 * @Project: MRC
 * @Author: JayJay
 * @Date: 2020/10/30
 * @ClassName: Test
 * @Description:
 */
public class Test {
    //声明需要爬取的网址
    static String URL="https://www.mzitu.com/page/2/";

    //主函数入口
    public static void main(String args[]) throws IOException {
        String result = HttpClientUtil.get(URL);

//        System.out.println(result);
        Document doc = Jsoup.parse(result);
        Elements postlist = doc.select(".postlist");
//        System.out.println(postlist.html());
        Elements li = postlist.select("li a");
//        li.forEach(item-> System.out.println(item.select("a")));
        System.out.println(li.select("a").get(0).attr("href"));
        String detail = HttpClientUtil.get(li.select("a").get(0).attr("href")+"/1");

        Document detailstr = Jsoup.parse(detail);
        System.out.println(detailstr.html());
        System.out.println(detailstr.select(".main-image img").attr("src"));
        System.out.println(detailstr.getElementsContainingText("下一»").attr("href"));
        String src = detailstr.select(".main-image img").attr("src");
//        String img = HttpClientUtil.get(src);
//        System.out.println(img);
        HttpClientUtil.getImg(src);


    }

}
