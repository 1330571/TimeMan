package com.xys.timemgr.service.impl;

import com.xys.timemgr.entity.Tag;
import com.xys.timemgr.mapper.TagMapper;
import com.xys.timemgr.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Xu Yusheng
 * @since 2020-12-17
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

}
