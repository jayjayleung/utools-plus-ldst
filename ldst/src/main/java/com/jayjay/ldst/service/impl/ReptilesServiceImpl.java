package com.jayjay.ldst.service.impl;

import com.jayjay.ldst.config.LdstUrl;
import com.jayjay.ldst.entity.LdstCover;
import com.jayjay.ldst.entity.LdstImg;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${upload.file.path}")
    private String path;

    @Autowired
    ILdstCoverService ldstCoverService;

    @Autowired
    ILdstImgService ldstImgService;

    /**
     * 爬取最新的图片
     *
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public List<LdstCover> reptliesNewest() throws Exception {
        String result = HttpClientUtil.get(LdstUrl.HOME);
        logger.info("开始爬取最新数据......");
        Document doc = Jsoup.parse(result);
        Elements newList = doc.select("body > div.main > div > div:nth-child(3) > div.tab_zt > div > div > ul").select("li");
        List<LdstCover> ldstCovers = this.doReptiles(newList);
        ldstCovers.forEach(item->item.setNewest(true));
        this.saveData(ldstCovers);
        logger.info("爬取最新完成......");
        return ldstCovers;
    }

    @Override
    @Transactional
    public List<LdstCover> reptliesByUrl(String url) throws Exception {
        String result = HttpClientUtil.get(url);
        Document doc = Jsoup.parse(result);
        List<LdstCover> ldstCovers = new ArrayList<>();
        String type = LdstUrl.map.get(url);
        logger.info("开始爬取"+type+"的数据......");
        Integer coverPageNumber = JsoupUtil.getCoverPageNumber(doc.html());
        logger.info("共"+coverPageNumber+"数据......");
        for (int i = 1; i <= coverPageNumber; i++) {
            String pageUrl = "_" + i + ".html";
            String coverUrl = url.replace(".html", pageUrl);
            logger.info("开始爬取第"+i+"页数据,url："+coverUrl+"......");
            String pageResult = HttpClientUtil.get(coverUrl);
            Document coverDoc = Jsoup.parse(pageResult);
            Elements elements = coverDoc.select("body > div.main > div > div.w1180.clearfix > div.Left_bar > div.list_cont.Left_list_cont.Left_list_cont2 > div.tab_tj > div > div > ul").select("li");
            List<LdstCover> pageList = this.doReptiles(elements);
            ldstCovers.addAll(pageList);
            logger.info("爬取第"+i+"页数据完成......");
        }
        this.saveData(ldstCovers);
        logger.info("爬取"+type+"的数据完成......");
        return ldstCovers;
    }

    @Override
    public List<LdstCover> reptliesAll() throws Exception {
        List<LdstCover> ldstCovers = new ArrayList<>();
        List<LdstCover> newests = this.reptliesNewest();
        List<LdstCover> sexGrils = this.reptliesByUrl(LdstUrl.SEX_GIRL);
        List<LdstCover> cosplays = this.reptliesByUrl(LdstUrl.COSPLAY);
        List<LdstCover> gameGirls = this.reptliesByUrl(LdstUrl.GAME_GIRL);
        List<LdstCover> airlineStewardess = this.reptliesByUrl(LdstUrl.AIRLINE_STEWARDESS);
        ldstCovers.addAll(newests);
        ldstCovers.addAll(sexGrils);
        ldstCovers.addAll(cosplays);
        ldstCovers.addAll(gameGirls);
        ldstCovers.addAll(airlineStewardess);
        return ldstCovers;
    }


    private List<LdstCover> doReptiles(Elements elements){
        List<LdstCover> ldstCovers = new ArrayList<>();
        if (!CollectionUtils.isEmpty(elements)) {
            elements.forEach(item -> {
                try {
                    LdstCover ldstCover = new LdstCover();
                    String coverUrl = JsoupUtil.getCoverUrl(item.html());
                    String detailUrl = JsoupUtil.getDetailUrl(item.html());
                    String title = JsoupUtil.getTitle(item.html());
                    logger.info("开始爬取" + title + "的数据.....");
                    List<LdstCover> listByTitle = ldstCoverService.findListByTitle(title);
                    if(!CollectionUtils.isEmpty(listByTitle)){
                        logger.info(title + "已存在，跳过......");
                        return;
                    }
                    ldstCover.setTitle(title);
                    ldstCover.setCoverOriginImgUrl(coverUrl);
                    ldstCover.setOriginUrl(detailUrl);
                    ldstCover.setCreateDate(LocalDateTime.now());
                    String detail = HttpClientUtil.get(detailUrl);
                    Integer detailPageNumber = JsoupUtil.getDetailPageNumber(detail);
                    ldstCover.setTotal(detailPageNumber);
                    String detailType = JsoupUtil.getDetailType(detail);
                    ldstCover.setType(detailType);
                    String date = JsoupUtil.getDate(detail);
                    LocalDateTime parse = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    ldstCover.setDate(parse);
//                    String coverPath = parse.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "/" + detailType + "/" + title + "/";
                    String coverPath = "/" + detailType + "/" + title + "/";
                    String savePath = path + coverPath;
                    logger.info("下载封面图片:" + coverUrl);
                    String coverImgUrl = HttpClientUtil.getImg(coverUrl, savePath);
                    ldstCover.setCoverImgUrl(coverPath + coverImgUrl);
                    List<LdstImg> ldstImgs = new ArrayList<>();
                    for (int i = 1; i < detailPageNumber; i++) {
                        LdstImg ldstImg = new LdstImg();
                        String pageUrl = "_" + i + ".html";
                        String detailPageUrl = detailUrl.replace(".html", pageUrl);
                        String detailHtml = HttpClientUtil.get(detailPageUrl);
                        String detailImgUrl = JsoupUtil.getDetailImgUrl(detailHtml);
                        ldstImg.setOriginImgUrl(detailImgUrl);
                        String detailImg = HttpClientUtil.getImg(detailImgUrl, savePath);
                        logger.info("下载图片:" + detailImgUrl);
                        ldstImg.setImgUrl(coverPath + detailImg);
                        ldstImg.setCreateDate(LocalDateTime.now());
                        ldstImg.setSequence(i);
                        String detailImgSize = JsoupUtil.getDetailImgSize(detailHtml);
                        ldstImg.setSize(detailImgSize);
                        ldstImgs.add(ldstImg);
                    }
                    ldstCover.setLdstImgList(ldstImgs);
                    ldstCovers.add(ldstCover);

                    logger.info("爬取" + title + "的完成.....");
//                    Random random = new Random();
//                    int randomInt = random.nextInt(10);
//                    Thread.sleep(randomInt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

//            ldstCovers.forEach(item -> {
//                boolean save = ldstCoverService.save(item);
//                if (save) {
//                    List<LdstImg> ldstImgList = item.getLdstImgList();
//                    if (!CollectionUtils.isEmpty(ldstImgList)) {
//                        ldstImgList.forEach(itm -> {
//                            itm.setCoverId(item.getId());
//                            ldstImgService.save(itm);
//                        });
//                    }
//                }
//            });

        }
        return ldstCovers;
    }

    public void saveData(List<LdstCover> ldstCovers){
        logger.info("开始保存数据.....");
        ldstCovers.forEach(item -> {
            boolean save = ldstCoverService.save(item);
            if (save) {
                List<LdstImg> ldstImgList = item.getLdstImgList();
                if (!CollectionUtils.isEmpty(ldstImgList)) {
                    ldstImgList.forEach(itm -> {
                        itm.setCoverId(item.getId());
                        ldstImgService.save(itm);
                    });
                }
            }
        });
        logger.info("保存数据完毕.....");
    }

}
