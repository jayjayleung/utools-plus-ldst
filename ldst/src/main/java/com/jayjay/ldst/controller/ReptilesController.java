package com.jayjay.ldst.controller;

import com.jayjay.ldst.config.LdstUrl;
import com.jayjay.ldst.config.ResultModel;
import com.jayjay.ldst.entity.LdstCover;
import com.jayjay.ldst.service.ReptilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jayjay
 * @date 2020/11/1
 * @Description:
 */
@RestController
@RequestMapping("/reptlie")
public class ReptilesController  {

//    @Autowired
//    ILdstCoverService ldstCoverService;
//
//    @Autowired
//    ILdstImgService ldstImgService;

    @Autowired
    ReptilesService reptilesService;

    /**
     * 爬取所有
     * @return
     * @throws Exception
     */
    @GetMapping("reptlies-all")
    public ResultModel reptliesAll() throws Exception{
        List<LdstCover> ldstCovers = reptilesService.reptliesAll();
        return ResultModel.Success(ldstCovers);
    }

    /**
     * 爬取最新
     * @return
     * @throws Exception
     */
    @GetMapping("reptlies-newest")
    public ResultModel reptlies() throws Exception{
        List<LdstCover> ldstCovers = reptilesService.reptliesNewest();
        return ResultModel.Success(ldstCovers);
    }


    /**
     * 爬取性感美女
     * @return
     * @throws Exception
     */
    @GetMapping("reptlies-sex-girl")
    public ResultModel reptliesSexGirl() throws Exception{
        List<LdstCover> ldstCovers = reptilesService.reptliesByUrl(LdstUrl.SEX_GIRL);
        return ResultModel.Success(ldstCovers);
    }


    /**
     * 爬取cosplay
     * @return
     * @throws Exception
     */
    @GetMapping("reptlies-cosplay")
    public ResultModel reptliesCosplay() throws Exception{
        List<LdstCover> ldstCovers = reptilesService.reptliesByUrl(LdstUrl.COSPLAY);
        return ResultModel.Success(ldstCovers);
    }

    /**
     * 爬取游戏女孩
     * @return
     * @throws Exception
     */
    @GetMapping("reptlies-game-girl")
    public ResultModel reptliesGameGirl() throws Exception{
        List<LdstCover> ldstCovers = reptilesService.reptliesByUrl(LdstUrl.GAME_GIRL);
        return ResultModel.Success(ldstCovers);
    }

    /**
     * 爬取空姐
     * @return
     * @throws Exception
     */
    @GetMapping("reptlies-airline-stewardess")
    public ResultModel reptliesAirlineStewardess() throws Exception{
        List<LdstCover> ldstCovers = reptilesService.reptliesByUrl(LdstUrl.AIRLINE_STEWARDESS);
        return ResultModel.Success(ldstCovers);
    }
}
