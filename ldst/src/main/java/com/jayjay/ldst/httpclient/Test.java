package com.jayjay.ldst.httpclient;

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
    static String URL="http://www.bky59.com/";
//    static String URL="https://www.google.com/";

    //主函数入口
    public static void main(String args[]) throws IOException {
        String result = HttpClientUtil.get(URL);

//        System.out.println(result);
        Document doc = Jsoup.parse(result);
        System.out.println(doc);
//        Elements postlist = doc.select(".postlist");
////        Elements nav = doc.select(".nav-links");
////        System.out.println(JsoupUtil.getCoverPageNumber(nav.html()));
//        Elements li = postlist.select("li");
//        li.forEach(item-> {
//            System.out.println(item.html());
//            System.out.println(JsoupUtil.getTitle(item.html()));
//            System.out.println(JsoupUtil.getCoverUrl(item.html()));
//            System.out.println(JsoupUtil.getDetailUrl(item.html()));
//            System.out.println(JsoupUtil.getCoverDate(item.html()));
//        });
////        System.out.println(li.select("a").get(0).attr("href"));
//        String detail = HttpClientUtil.get(li.select("a").get(0).attr("href")+"/1");
////
//        Document detailstr = Jsoup.parse(detail);
////        System.out.println(detailstr.html());
//        System.out.println(JsoupUtil.getDetailPageNumber(detailstr.html()));
//        System.out.println(JsoupUtil.getDetailType(detailstr.html()));
//        System.out.println(JsoupUtil.getDetailImgUrl(detailstr.html()));
//        System.out.println(detailstr.select(".main-image img").first().attr("src"));
//        System.out.println(detailstr.getElementsContainingText("下一»").attr("href"));
//        String src = detailstr.select(".main-image img").attr("src");
//        String img = HttpClientUtil.get(src);
//        System.out.println(img);
//        HttpClientUtil.getImg(src);


    }

    /**
     * 爬IP池
     */
    @org.junit.Test
    public void getIpPool() {

        List<String> list = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://www.xicidaili.com/wn/").userAgent("Mozilla").cookie("auth", "token").timeout(3000).get();

            Element el = doc.getElementById("wrapper");
            Element el2 = el.getElementById("body");
            Elements els = el2.select("table").select("tr");
            System.out.println("===================");

            for (int i = 1; i < els.size(); i++) {
                try {
                    Elements tds = els.get(i).select("td");
                    String[] ss = tds.text().split(" ");
                    String ip = ss[0];
                    String port = ss[1];
                    System.out.println(ip + "=" + port);
                    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ss[0], Integer.parseInt(ss[1])));
                    URLConnection httpCon = new URL("https://gz.esf.leju.com/community/f4-n3-a1442/")
                            .openConnection(proxy);
                    httpCon.setConnectTimeout(15000);
                    httpCon.setReadTimeout(15000);
                    int code = ((HttpURLConnection) httpCon).getResponseCode();

                    System.out.println(code == 200);
                    if (code == 200) {

                        System.out.println("成功");
                        list.add(ip + "=" + port);
                    } else {
                        System.out.println("失败");
                    }
                    System.out.println("===================分割线==============");
                } catch (Exception e) {
                    System.out.println("失败");
                    System.out.println("===================分割线==============");
                    continue;
                }
            }
            System.out.println("===================");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("加载文档出错");
        }
    }

}
