package com.jayjay.ldst.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jayjay.ldst.config.Page;
import com.jayjay.ldst.entity.LdstImg;
import com.jayjay.ldst.mapper.LdstImgMapper;
import com.jayjay.ldst.service.ILdstImgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JayJay
 * @since 2020-11-02
 */
@Service
public class LdstImgServiceImpl extends ServiceImpl<LdstImgMapper, LdstImg> implements ILdstImgService {

    @Override
    public Page<LdstImg> getPageSimple(Page<LdstImg> page) {
        int pageNum = page.getPage();
        int pageSize = page.getPageSize();
        int startNum = (pageNum - 1) * pageSize;
        //拼接分页数据
        Map m = page.getMap();
        m.put("startNum", startNum);
        m.put("pageSize", pageSize);
        m.put("dir", page.getDir());
        m.put("sort", page.getSort());
        //设置查询数据
        List list = this.baseMapper.findListByMap(m);
        int count = this.baseMapper.getCount(m);
        page.setCollection(list);
        page.setTotalSize(count);
        return page;
    }

    @Override
    public List<LdstImg> getList(String id) {
        QueryWrapper<LdstImg> wrapper = new QueryWrapper<>();
        wrapper.eq("cover_id",id);
        List<LdstImg> ldstImgs = this.baseMapper.selectList(wrapper);
        return ldstImgs;
    }
}
