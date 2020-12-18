package com.xys.timemgr.controller;


import com.xys.timemgr.entity.Comment;
import com.xys.timemgr.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Xu Yusheng
 * @since 2020-12-17
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentMapper commentMapper;

    @PostMapping("/makeComment")
    public String makeComment(@RequestBody Comment comment){
        System.out.println(comment);
        if(comment == null) return "Bad Request";
        if(comment.getComment().length() > 200) return "Size Limited Exceed";
        commentMapper.insert(comment);
        return "success";
    }
}
