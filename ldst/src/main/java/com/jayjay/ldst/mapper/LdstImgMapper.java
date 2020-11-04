package com.jayjay.ldst.mapper;

import com.jayjay.ldst.entity.LdstCover;
import com.jayjay.ldst.entity.LdstImg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author JayJay
 * @since 2020-11-02
 */
public interface LdstImgMapper extends BaseMapper<LdstImg> {
    /**
     * 获取集合，分页，mybastis-plus默认内存分页，自己自定义分页查询
     *
     * @param m
     * @return
     */
    List<LdstImg> findListByMap(Map<String, Object> m);

    /**
     * 获取总条数
     *
     * @param m
     * @return
     */
    int getCount(Map<String, Object> m);

    /**
     * 获取单个
     *
     * @param id
     * @return
     */
    LdstImg get(String id);
}
