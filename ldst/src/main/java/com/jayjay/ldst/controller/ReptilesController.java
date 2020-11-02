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

    @GetMapping("reptliesAll")
    public ResultModel reptliesAll() throws Exception{
        List<LdstCover> ldstCovers = reptilesService.reptliesAll();
        return ResultModel.Success(ldstCovers);
    }

    @GetMapping("reptliesNewest")
    public ResultModel reptlies() throws Exception{
        List<LdstCover> ldstCovers = reptilesService.reptliesNewest();
        return ResultModel.Success(ldstCovers);
    }


    @GetMapping("reptliesSexGirl")
    public ResultModel reptliesSexGirl() throws Exception{
        List<LdstCover> ldstCovers = reptilesService.reptliesByUrl(LdstUrl.SEX_GIRL);
        return ResultModel.Success(ldstCovers);
    }


    @GetMapping("reptliesCosplay")
    public ResultModel reptliesCosplay() throws Exception{
        List<LdstCover> ldstCovers = reptilesService.reptliesByUrl(LdstUrl.COSPLAY);
        return ResultModel.Success(ldstCovers);
    }

    @GetMapping("reptliesGameGirl")
    public ResultModel reptliesGameGirl() throws Exception{
        List<LdstCover> ldstCovers = reptilesService.reptliesByUrl(LdstUrl.GAME_GIRL);
        return ResultModel.Success(ldstCovers);
    }

    @GetMapping("reptliesAirlineStewardess")
    public ResultModel reptliesAirlineStewardess() throws Exception{
        List<LdstCover> ldstCovers = reptilesService.reptliesByUrl(LdstUrl.AIRLINE_STEWARDESS);
        return ResultModel.Success(ldstCovers);
    }
}
