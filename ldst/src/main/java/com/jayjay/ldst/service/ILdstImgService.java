package com.jayjay.ldst.service;

import com.jayjay.ldst.config.Page;
import com.jayjay.ldst.entity.LdstCover;
import com.jayjay.ldst.entity.LdstImg;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JayJay
 * @since 2020-11-02
 */
public interface ILdstImgService extends IService<LdstImg> {

    Page<LdstImg> getPageSimple(Page<LdstImg> page);

    List<LdstImg> getList(String id);
}
