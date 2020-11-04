package com.jayjay.ldst.controller;


import com.jayjay.ldst.config.Page;
import com.jayjay.ldst.config.ResultModel;
import com.jayjay.ldst.entity.LdstCover;
import com.jayjay.ldst.entity.LdstImg;
import com.jayjay.ldst.service.ILdstImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 获取Lsit，分页
     * @param page
     * @return
     */
    @PostMapping("list")
    public ResultModel list(@RequestBody(required = false) Page<LdstImg> page){
        page = ldstImgService.getPageSimple(page);
        return ResultModel.Success(page);
    }


    /**
     * 获取所有
     * @param id
     * @return
     */
    @GetMapping("list-all")
    public ResultModel listAll(String id){
        List<LdstImg> list = ldstImgService.getList(id);
        return ResultModel.Success(list);
    }
}

