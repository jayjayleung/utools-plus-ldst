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

    List<LdstCover> findListByTitle(String title);

    List<LdstCover> findListByType(String title);

    List<LdstCover> findListByDate(LocalDateTime start, LocalDateTime end);

    Page<LdstCover> getPageSimple(Page<LdstCover> page);

    List<String> selectType();
}
