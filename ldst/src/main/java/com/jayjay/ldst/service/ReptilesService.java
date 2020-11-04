package com.jayjay.ldst.service;

import com.jayjay.ldst.entity.LdstCover;

import java.util.List;

/**
 * @author jayjay
 * @date 2020/11/1
 * @Description:
 */
public interface ReptilesService {

    /**
     * 爬取最新的图片
     * @return
     * @throws Exception
     */
    List<LdstCover> reptliesNewest() throws Exception;

    /**
     * 根据url爬取数据
     * @param url
     * @return
     * @throws Exception
     */
    List<LdstCover> reptliesByUrl(String url) throws Exception;


    /**
     * 爬取所有数据
     * @return
     * @throws Exception
     */
    List<LdstCover> reptliesAll() throws Exception;
}
