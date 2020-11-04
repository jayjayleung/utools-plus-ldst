package com.jayjay.ldst.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jayjay.ldst.config.Page;
import com.jayjay.ldst.entity.LdstCover;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JayJay
 * @since 2020-11-02
 */
public interface ILdstCoverService extends IService<LdstCover> {

    /**
     * 根据title获取
     * @param title
     * @return
     */
    List<LdstCover> findListByTitle(String title);

    /**
     * 根据类型获取
     * @param title
     * @return
     */
    List<LdstCover> findListByType(String title);

    /**
     * 根据时间范围获取
     * @param start
     * @param end
     * @return
     */
    List<LdstCover> findListByDate(LocalDateTime start, LocalDateTime end);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<LdstCover> getPageSimple(Page<LdstCover> page);

    /**
     * 获取类型
     * @return
     */
    List<String> selectType();
}
