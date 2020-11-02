package com.jayjay.ldst.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jayjay.ldst.entity.LdstCover;
import com.jayjay.ldst.mapper.LdstCoverMapper;
import com.jayjay.ldst.service.ILdstCoverService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JayJay
 * @since 2020-11-02
 */
@Service
public class LdstCoverServiceImpl extends ServiceImpl<LdstCoverMapper, LdstCover> implements ILdstCoverService {



    public List<LdstCover> findListByTitle(String title){
        QueryWrapper<LdstCover> wrapper = new QueryWrapper<>();
        wrapper.eq("title",title);
        List<LdstCover> list = this.list(wrapper);
        return list;
    }

    public List<LdstCover> findListByDate(LocalDateTime start,LocalDateTime end){
        QueryWrapper<LdstCover> wrapper = new QueryWrapper<>();
        wrapper.between("date",start,end);
        List<LdstCover> list = this.list(wrapper);
        return list;
    }
}
