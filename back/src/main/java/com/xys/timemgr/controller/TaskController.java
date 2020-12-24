package com.xys.timemgr.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xys.timemgr.entity.Task;
import com.xys.timemgr.entity.User;
import com.xys.timemgr.mapper.TaskMapper;
import com.xys.timemgr.mapper.UserMapper;
import com.xys.timemgr.service.impl.TaskServiceImpl;
import com.xys.timemgr.utils.DataConvert;
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

    @Autowired
    TaskServiceImpl taskService;

    @Autowired
    UserMapper userMapper;

    @PostMapping("/submitPersonal")
    public String submitPersonalTask(@RequestBody Task task) {
        System.out.println("Submit " + task);
        if (task == null) return "Bad Request";
        taskService.save(task);
        //then put this task into user
        User user = userMapper.selectById(task.getUserList());
        System.out.println(user.toString());
        user.setTasks(user.getTasks() + "_" + task.getId());
        userMapper.updateById(user);
        return "Ok";
    }

    @GetMapping("/queryTask/{id}")
    public List<Task> queryTasksById(@PathVariable("id") Integer id) {
        System.out.println("Query " + id + " ...task List");
        return taskMapper.selectList(new QueryWrapper<Task>().eq("user_list", id));
    }

    @PostMapping("/queryTaskByIdArr")
    public List<Task> queryTasksByIdArr(@RequestBody StringWrapper stringWrapper) throws InterruptedException {
        Thread.sleep(1);
        String[] strings = stringWrapper.getData();
        System.out.println("QueryTaskByIDAddr" + Arrays.toString(strings));
        ArrayList<Task> arrayList = new ArrayList<>();
        System.out.println("Query " + Arrays.toString(strings));
        for (String task : strings) {
            arrayList.add(taskMapper.selectOne(new QueryWrapper<Task>().eq("id", Integer.parseInt(task))));
        }
        return arrayList;
    }

    @GetMapping("/task/{id}")
    public Task getTaskByID(@PathVariable("id") Integer id){
        return taskMapper.selectById(id);
    }

    @GetMapping("/getState/{userID}/{taskID}")
    public int queryTask(@PathVariable("userID") Integer id, @PathVariable("taskID") Integer taskId) {
        Task task = taskMapper.selectById(taskId);
        String[] taskStr = DataConvert.splitString(task.getUserList());
        String[] statusStr = DataConvert.splitString(task.getStatesList());
        int length = taskStr.length;
        for (int i = 0; i < length; ++i)
            if (Integer.parseInt(taskStr[i]) == id)
                return Integer.parseInt(statusStr[i]);
        return 4;
    }

    @GetMapping("/setState/{userID}/{taskID}/{status}")
    public void setTask(@PathVariable("userID") Integer id, @PathVariable("taskID") Integer taskId, @PathVariable("status") Integer status) {
        Task task = taskMapper.selectById(taskId);
        String[] taskStr = DataConvert.splitString(task.getUserList());
        String[] statusStr = DataConvert.splitString(task.getStatesList());
        int length = taskStr.length;
        for (int i = 0; i < length; ++i)
            if (Integer.parseInt(taskStr[i]) == id)
                statusStr[i] = status.toString();

        String newTaskStr = DataConvert.concatString(taskStr);
        String newStatusStr = DataConvert.concatString(statusStr);

        task.setStatesList(newStatusStr);
        taskMapper.updateById(task);
    }

    @GetMapping("/getComments/{taskId}")
    public String[] getComments(@PathVariable("taskId") Integer id) {
        Task task = taskMapper.selectById(id);
        return DataConvert.splitString(task.getCommentsId());
    }

    @GetMapping("/search/{searchStr}")
    public ArrayList<Task> search(@PathVariable("searchStr") String string){
        ArrayList<Task> arrayList = new ArrayList<>();

        return arrayList;
    }

    @GetMapping("/insertComment/{taskID}/{commentID}")
    public String insertComment(@PathVariable("taskID") Integer taskId, @PathVariable("commentID") Integer id) {
        Task task = taskMapper.selectById(taskId);
        String str = task.getCommentsId();
        ArrayList<String> arrayList;
        if (str != null) {
            arrayList = (ArrayList<String>) DataConvert.splitStringList(str);
        } else {
            arrayList = new ArrayList<>();
        }
        arrayList.add(id.toString());
        task.setCommentsId(DataConvert.concatString(arrayList));
        taskMapper.updateById(task);
        return "Comment Insert Into Task DB";
    }
}

@Data
class StringWrapper {
    private String[] data;
}
