package com.jayjay.ldst.mapper;

import com.jayjay.ldst.entity.LdstCover;
import com.jayjay.ldst.entity.LdstImg;
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
public interface LdstImgMapper extends BaseMapper<LdstImg> {
    List<LdstImg> findListByMap(Map<String, Object> m);

    int getCount(Map<String, Object> m);

    LdstImg get(String id);
}
