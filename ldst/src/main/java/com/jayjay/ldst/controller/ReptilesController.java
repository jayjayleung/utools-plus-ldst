package com.jayjay.ldst.controller;

import com.jayjay.ldst.config.ResultModel;
import com.jayjay.ldst.service.ILdstCoverService;
import com.jayjay.ldst.service.ILdstImgService;
import com.jayjay.ldst.service.ReptilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jayjay
 * @date 2020/11/1
 * @Description:
 */
@RestController
@RequestMapping("/reptlie")
public class ReptilesController  {

    @Autowired
    ILdstCoverService ldstCoverService;

    @Autowired
    ILdstImgService ldstImgService;

    @Autowired
    ReptilesService reptilesService;


    @GetMapping
    public ResultModel reptlies(){

        return ResultModel.Success();
    }
}
