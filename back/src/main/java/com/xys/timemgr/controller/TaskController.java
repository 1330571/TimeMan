package com.xys.timemgr.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xys.timemgr.entity.Task;
import com.xys.timemgr.mapper.TaskMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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
        System.out.println("Submit " + task);
        if (task == null) return "Bad Request";
//        taskMapper.insert(task);
        return "Heart Beat";
    }

    @GetMapping("/queryTask/{id}")
    public List<Task> queryTasksById(@PathVariable("id") Integer id) {
        System.out.println("Query " + id + " ...task List");
        return taskMapper.selectList(new QueryWrapper<Task>().eq("user_list", id));
    }

    @PostMapping("/queryTaskByIdArr")
    public List<Task> queryTasksByIdArr(@RequestBody StringWrapper stringWrapper) {
        String[] strings = stringWrapper.getData();
        System.out.println("QueryTaskByIDAddr" + Arrays.toString(strings));
        ArrayList<Task> arrayList = new ArrayList<>();
        System.out.println("Query " + Arrays.toString(strings));
        for (String task : strings) {
            arrayList.add(taskMapper.selectOne(new QueryWrapper<Task>().eq("id", Integer.parseInt(task))));
        }
        return arrayList;
    }
}

@Data
class StringWrapper{
    private String[] data;
}
