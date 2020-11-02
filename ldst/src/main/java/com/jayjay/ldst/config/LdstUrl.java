package com.jayjay.ldst.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jayjay
 * @date 2020/11/1
 * @Description:
 */
public class LdstUrl {
//    public static String home = "https://www.mzitu.com/";
    //主页获取最新
    public static String HOME = "http://www.win4000.com/meitu.html";
    //性感美女
    public static String SEX_GIRL = "http://www.win4000.com/meinvtag4.html";
    //cosplay
    public static String COSPLAY = "http://www.win4000.com/meinvtag26.html";
    //游戏女孩
    public static String GAME_GIRL = "http://www.win4000.com/meinvtag7.html";
    //空姐
    public static String AIRLINE_STEWARDESS = "http://www.win4000.com/meinvtag6.html";

    public static Map<String,String> map = new HashMap<>();

    static {
        map.put(HOME,"最新图片");
        map.put(SEX_GIRL,"性感美女");
        map.put(COSPLAY,"cosplay");
        map.put(GAME_GIRL,"游戏女孩");
        map.put(AIRLINE_STEWARDESS,"空姐");
    }



}
