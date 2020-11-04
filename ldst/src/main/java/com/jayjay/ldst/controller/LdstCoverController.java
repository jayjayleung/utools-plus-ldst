package com.jayjay.ldst.controller;


import com.jayjay.ldst.config.Page;
import com.jayjay.ldst.config.ResultModel;
import com.jayjay.ldst.entity.LdstCover;
import com.jayjay.ldst.service.ILdstCoverService;
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
@RequestMapping("/ldst-cover")
public class LdstCoverController {

    @Autowired
    private ILdstCoverService ldstCoverService;


    /**
     * 获取list，分页
     * @param page
     * @return
     */
    @PostMapping("list")
    public ResultModel list(@RequestBody(required = false) Page<LdstCover> page){
        page = ldstCoverService.getPageSimple(page);
        return ResultModel.Success(page);
    }

    /**
     * 获取类型，去重
     * @return
     */
    @GetMapping("get-types")
    public ResultModel getTypes(){
        List<String> strings = ldstCoverService.selectType();
        return ResultModel.Success(strings);
    }

}

