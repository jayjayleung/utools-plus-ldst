package com.jayjay.ldst.mapper;

import com.jayjay.ldst.entity.LdstCover;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JayJay
 * @since 2020-11-02
 */
public interface LdstCoverMapper extends BaseMapper<LdstCover> {

    /**
     * 获取集合，分页，mybastis-plus默认内存分页，自己自定义分页查询
     * @param m
     * @return
     */
    List<LdstCover> findListByMap(Map<String, Object> m);

    /**
     * 获取总条数
     * @param m
     * @return
     */
    int getCount(Map<String, Object> m);

    /**
     * 获取单个
     * @param id
     * @return
     */
    LdstCover get(String id);

    /**
     * 获取类型，去重
     * @return
     */
    List<String> selectType();
}
