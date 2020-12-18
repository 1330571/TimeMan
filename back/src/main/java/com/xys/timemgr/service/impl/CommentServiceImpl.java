package com.xys.timemgr.service.impl;

import com.xys.timemgr.entity.Comment;
import com.xys.timemgr.mapper.CommentMapper;
import com.xys.timemgr.service.ICommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
