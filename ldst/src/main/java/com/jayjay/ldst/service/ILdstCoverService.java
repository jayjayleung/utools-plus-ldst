package com.jayjay.ldst.service;

import com.jayjay.ldst.entity.LdstCover;
import com.baomidou.mybatisplus.extension.service.IService;

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

    List<LdstCover> findListByDate(LocalDateTime start, LocalDateTime end);
}
