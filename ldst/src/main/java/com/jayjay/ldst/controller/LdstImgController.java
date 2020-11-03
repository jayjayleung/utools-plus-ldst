package com.jayjay.ldst.controller;


import com.jayjay.ldst.config.Page;
import com.jayjay.ldst.config.ResultModel;
import com.jayjay.ldst.entity.LdstCover;
import com.jayjay.ldst.entity.LdstImg;
import com.jayjay.ldst.service.ILdstImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JayJay
 * @since 2020-11-02
 */
@RestController
@RequestMapping("/ldst-img")
public class LdstImgController {

    @Autowired
    private ILdstImgService ldstImgService;

    @PostMapping("list")
    public ResultModel list(@RequestBody(required = false) Page<LdstImg> page){
        page = ldstImgService.getPageSimple(page);
        return ResultModel.Success(page);
    }


    @PostMapping("list-all")
    public ResultModel listAll(String id){
        List<LdstImg> list = ldstImgService.getList(id);
        return ResultModel.Success(list);
    }
}

