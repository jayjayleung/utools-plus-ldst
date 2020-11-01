package com.jayjay.ldst.service.impl;

import com.jayjay.ldst.config.LdstUrl;
import com.jayjay.ldst.entity.LdstCover;
import com.jayjay.ldst.service.ILdstCoverService;
import com.jayjay.ldst.service.ILdstImgService;
import com.jayjay.ldst.service.ReptilesService;
import com.jayjay.ldst.util.HttpClientUtil;
import com.jayjay.ldst.util.JsoupUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author jayjay
 * @date 2020/11/1
 * @Description:
 */
@Service
public class ReptilesServiceImpl implements ReptilesService {

    @Value("${upload.file.path}")
    private String path;

    @Autowired
    ILdstCoverService ldstCoverService;

    @Autowired
    ILdstImgService ldstImgService;


    @Test
    public void doReptlies() throws IOException, InterruptedException {

        String result = HttpClientUtil.get(LdstUrl.home);
        Document doc = Jsoup.parse(result);
        String coverPageNumber = JsoupUtil.getCoverPageNumber(doc.html());
        if(StringUtils.isNotEmpty(coverPageNumber)){
            Integer coverPageNumber2Int = Integer.valueOf(coverPageNumber);
            for (int i = 1; i <= coverPageNumber2Int; i++) {


                result = HttpClientUtil.get(LdstUrl.home+"page/"+i+"/");
                doc = Jsoup.parse(result);
                Elements postlist = doc.select(".postlist");
                Elements li = postlist.select("li");
                List<LdstCover> ldstCoverList = new ArrayList<>();
                li.forEach(item-> {

                    System.out.println(item.html());
                    System.out.println(JsoupUtil.getTitle(item.html()));
                    System.out.println(JsoupUtil.getCoverUrl(item.html()));
                    System.out.println(JsoupUtil.getDetailUrl(item.html()));
                    System.out.println(JsoupUtil.getCoverDate(item.html()));

                    Random random = new Random();
                    int randomInt = random.nextInt(10);
                    try {
                        Thread.sleep(randomInt);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

                Random random = new Random();
                int randomInt = random.nextInt(10);
                Thread.sleep(randomInt);

            }
        }

    }
}
