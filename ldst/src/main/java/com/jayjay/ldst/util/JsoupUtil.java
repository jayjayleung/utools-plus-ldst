package com.jayjay.ldst.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author jayjay
 * @date 2020/11/1
 * @Description:
 */
public class JsoupUtil {

    public static String getTitle(String html){
        Document doc = Jsoup.parse(html);
        String title = doc.select("a").get(1).text();
        return title;
    }

    public static String getDetailUrl(String html){
        Document doc = Jsoup.parse(html);
        String url = doc.select("a").get(0).attr("href");
        return url;
    }

    public static String getCoverUrl(String html){
        Document doc = Jsoup.parse(html);
        String url = doc.select("a").get(0).select(".lazy").attr("data-original");
        return url;
    }

    public static String getCoverDate(String html){
        Document doc = Jsoup.parse(html);
        String time = doc.select(".time").text();
        return time;
    }

    public static String getCoverPageNumber(String html){
        Document doc = Jsoup.parse(html);
//        System.out.println(html);
        Elements select = doc.select(".page-numbers");
        String number = select.get(select.size()-2).text();
        return number;
    }

    public static String getDetailPageNumber(String html){
        Document doc = Jsoup.parse(html);
        Elements select = doc.select(".pagenavi").select("a");
        String number = select.get(select.size()-2).text();
        return number;
    }

    public static String getDetailType(String html){
        Document doc = Jsoup.parse(html);
        Element select = doc.select(".main-meta").select("a").first();
        String type = select.text();
        return type;
    }

    public static String getDetailImgUrl(String html){
        Document doc = Jsoup.parse(html);
        Element select = doc.select(".main-image img").first();
        String url = select.attr("src");
        return url;
    }
}
