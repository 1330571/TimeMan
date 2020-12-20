package com.xys.timemgr.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xys.timemgr.entity.Task;
import com.xys.timemgr.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskMapper taskMapper;

    @PostMapping("/submitPersonal")
    public String submitPersonalTask(@RequestBody Task task) {
        System.out.println("submit " + task);
        if (task == null) return "Bad Request";
//        taskMapper.insert(task);
        return "Heart Beat";
    }

    @GetMapping("/queryTask/{id}")
    public List<Task> queryTasksById(@PathVariable("id") Integer id) {
        System.out.println("Query " + id + " ...task List");
        return taskMapper.selectList(new QueryWrapper<Task>().eq("user_list", id));
    }
}
