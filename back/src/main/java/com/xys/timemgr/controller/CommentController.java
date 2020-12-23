package com.xys.timemgr.controller;


import com.xys.timemgr.entity.Comment;
import com.xys.timemgr.entity.Task;
import com.xys.timemgr.mapper.CommentMapper;
import com.xys.timemgr.mapper.TaskMapper;
import com.xys.timemgr.utils.DataConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
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

    @Autowired
    TaskMapper taskMapper;

    @PostMapping("/makeComment")
    public String makeComment(@RequestBody Comment comment) {
        System.out.println(comment);
        if (comment == null) return "Bad Request";
        if (comment.getComment().length() > 200) return "Size Limited Exceed";
        commentMapper.insert(comment);
        return comment.getId().toString();
    }

    @PostMapping("/getComment")
    public List<Comment> getComments(@RequestBody StringWrapper stringWrapper) {
        System.out.println("Query Comments -> " + stringWrapper.toString());
        String[] dataArr = stringWrapper.getData();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (String str : dataArr) {
            arrayList.add(Integer.parseInt(str));
        }
        return commentMapper.selectBatchIds(arrayList);
    }

    @GetMapping("/getAllComments/{id}")
    public List<Comment> getAllComments(@PathVariable("id") Integer id) {
        Task task = taskMapper.selectById(id);
        String[] commentArr = DataConvert.splitString(task.getCommentsId());
        ArrayList<Integer> ids = new ArrayList<>();
        for (String commentID : commentArr)
            ids.add(Integer.parseInt(commentID));
        return commentMapper.selectBatchIds(ids);
    }

}
