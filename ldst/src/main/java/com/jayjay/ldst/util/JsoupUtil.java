package com.jayjay.ldst.util;

import org.apache.commons.lang3.StringUtils;
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



    /**
     * 获取标题
     * @param html
     * @return
     */
    public static String getTitle(String html){
        Document doc = Jsoup.parse(html);
        String title = doc.select("a > p").first().text();
        return title;
    }


    /**
     * 获取封面url
     * @param html
     * @return
     */
    public static String getCoverUrl(String html){
        Document doc = Jsoup.parse(html);
        String url = doc.select("a > img").first().attr("data-original");
        return url;
    }

    /**
     * 获取详情页url
     * @param html
     * @return
     */
    public static String getDetailUrl(String html){
        Document doc = Jsoup.parse(html);
        String url = doc.select("a").first().attr("href");
        return url;
    }

    /**
     * 获取发布时间
     * @param html
     * @return
     */
    public static String getDate(String html){
        Document doc = Jsoup.parse(html);
        String time = doc.select(".Bigimg_style > .time").first().text();
        return time;
    }

    /**
     * 获取封面总页码数
     * @param html
     * @return
     */
    public static Integer getCoverPageNumber(String html){
        Document doc = Jsoup.parse(html);
//        System.out.println(html);
        Element select = doc.select(".pages .num").last();
        String number = select.text();
        return StringUtils.isNotEmpty(number)?Integer.valueOf(number):0;
    }

    /**
     * 获取详情页码数
     * @param html
     * @return
     */
    public static Integer getDetailPageNumber(String html){
        Document doc = Jsoup.parse(html);
        Element select = doc.select(".ptitle > em").first();
        String number = select.text();
        return StringUtils.isNotEmpty(number)?Integer.valueOf(number):0;
    }

    /**
     * 获取图片大小
     * @param html
     * @return
     */
    public static String getDetailImgSize(String html){
        Document doc = Jsoup.parse(html);
        Element select = doc.select(".Bigimg_style > .size").first();
        String number = select.text();
        if(StringUtils.isNotEmpty(number)){
            number = number.split("：")[1];
        }
        return number;
    }

    /**
     * 获取图片类型
     * @param html
     * @return
     */
    public static String getDetailType(String html){
        Document doc = Jsoup.parse(html);
        Element select = doc.select(".breadcrumbs > a").last();
        String type = select.text();
        return type;
    }

    /**
     * 获取详情图片Url
     * @param html
     * @return
     */
    public static String getDetailImgUrl(String html){
        Document doc = Jsoup.parse(html);
        Element select = doc.select(".pic-large").first();
        String url = select.attr("data-original");
        return url;
    }
}
