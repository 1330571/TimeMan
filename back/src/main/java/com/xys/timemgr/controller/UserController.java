package com.xys.timemgr.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xys.timemgr.entity.Task;
import com.xys.timemgr.entity.User;
import com.xys.timemgr.mapper.TaskMapper;
import com.xys.timemgr.mapper.UserMapper;
import com.xys.timemgr.service.impl.UserServiceImpl;
import com.xys.timemgr.utils.DataConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Xu Yusheng
 * @since 2020-12-17
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    TaskMapper taskMapper;

    public String update(Integer id) {
        User user = userMapper.selectById(id);
        String taskList = user.getTasks();
        String[] taskArr = DataConvert.splitString(taskList);
        for (String taskId : taskArr) {
            Task task = taskMapper.selectById(taskId);
            LocalDateTime localDateTime = task.getDeadline();
            Date deadline = Date.from(localDateTime.toInstant(ZoneOffset.of("+8"))); //Chinese Time Zone
            Date date = new Date();
            if (deadline.before(date)) {
                String userArrStr = task.getUserList();
                String statusArrStr = task.getStatesList();
                String[] userArr = DataConvert.splitString(userArrStr);
                String[] statusArr = DataConvert.splitString(statusArrStr);
                boolean find = false;
                for (int i = 0; i < userArr.length; ++i) {
                    if (userArr[i].equals(id.toString())) {
                        int status = Integer.parseInt(statusArr[i]);
                        if (status != 4 && status != 2) {
                            // set the task status to overdue
                            statusArr[i] = "3";
                            task.setStatesList(DataConvert.concatString(statusArr));
                            find = true;
                            break;
                        }
                    }
                }
                if (find) {
                    taskMapper.updateById(task);
                }
            }
        }
        return "update";
    }

    @GetMapping("/getName/{id}")
    public String getNameByUserAccount(@PathVariable("id") String account) {
        System.out.println("Query ... " + account);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("user_account", account);
        return userService.getOne(new QueryWrapper<User>().eq("user_account", account)).getUserName();
    }
//        return userService.getOne(queryWrapper).getUserName();

    @PostMapping("/login")
    public String login(@RequestBody User user, HttpSession session) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(user);
        if (user == null) return "Bad Request";
        User _user = userService.getOne(new QueryWrapper<User>().eq("user_account", user.getUserAccount()));
        update(_user.getId());
        if (user.getPassword().equals(_user.getPassword())) {
            session.setAttribute("loginUser", _user.getId().toString());
            return _user.getId().toString();
        } else
            return "Error";
    }

    @GetMapping("/query/{id}")
    public User query(@PathVariable("id") Integer id) {
        System.out.println("Query ... " + id);
        return userMapper.selectById(id);
    }

    @GetMapping("/getTaskIds/{id}")
    public String[] getTaskIds(@PathVariable("id") Integer id) {
        System.out.println("Query All Tasks ..." + id);
        return DataConvert.splitString(userService.getOne(new QueryWrapper<User>().eq("id", id)).getTasks());
    }

    @GetMapping("/getGroupIds/{id}")
    public String[] getGroupIds(@PathVariable("id") Integer id) {
        System.out.println("Query All Groups ..." + id);
        return DataConvert.splitString(userService.getOne(new QueryWrapper<User>().eq("id", id)).getUserGroups());
    }

    @GetMapping("/deleteTask/{id}/{taskId}")
    public void deleteTask(@PathVariable("id") Integer id, @PathVariable("taskId") Integer taskId) {
        System.out.println("delete " + id + " -> " + taskId);
        User user = userMapper.selectById(id);
        ArrayList<String> arr = new ArrayList<>();
        for (String str : DataConvert.splitString(user.getTasks())) {
            if (!str.equals(taskId.toString()))
                arr.add(str);
        }
        String[] arr2 = arr.toArray(new String[0]);
        user.setTasks(DataConvert.concatString(arr2));
        userMapper.updateById(user);
    }

}
