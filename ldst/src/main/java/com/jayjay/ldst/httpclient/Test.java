package com.jayjay.ldst.httpclient;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import com.jayjay.ldst.util.HttpClientUtil;
import com.jayjay.ldst.util.JsoupUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

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
//    static String URL="https://www.mzitu.com/page/1/";
    static String URL="http://www.win4000.com/meinvtag26_1.html";
//    static String URL="https://www.google.com/";

    //主函数入口
    public static void main(String args[]) throws IOException, XpathSyntaxErrorException {
        String result = HttpClientUtil.get(URL);
        Document doc = Jsoup.parse(result);
//        System.out.println(doc);
        System.out.println(JsoupUtil.getCoverPageNumber(doc.html()));


//        JXDocument jxDocument = new JXDocument(doc);
//        List<JXNode> jxNodes = jxDocument.selN("/html/body/div[4]/div/div[3]/div[2]/div/div/ul"); document.querySelector("")

        Elements select = doc.select("body > div.main > div > div.w1180.clearfix > div.Left_bar > div.list_cont.Left_list_cont.Left_list_cont2 > div.tab_tj > div > div > ul");
//        jxNodes.forEach(item-> System.out.println(item.toString()));
        System.out.println(select.html());
//        System.out.println(select.select("a").first().attr(""));
//        System.out.println(select.select("a > p").text());
//        System.out.println(select.get(0).select("a > img").first().attr("data-original"));


    }


//    //声明需要爬取的网址
////    static String URL="https://www.mzitu.com/page/1/";
////    static String URL="http://www.win4000.com/meitu.html";
//    static String URL="http://www.win4000.com/meinv216624_1.html";
//
//    //主函数入口
//    public static void main(String args[]) throws IOException, XpathSyntaxErrorException {
//        String result = HttpClientUtil.get(URL);
//        Document doc = Jsoup.parse(result);
//        System.out.println(doc);
//        System.out.println(doc.select(".breadcrumbs > a").last().text());
//        System.out.println(doc.select(".Bigimg_style > .time").first().text());
//        System.out.println(doc.select(".Bigimg_style > .size").first().text().split("：")[1]);
//        System.out.println(doc.select(".ptitle > em").first().text());
//        System.out.println(doc.select(".pic-large").first().attr("data-original"));
//    }

}
