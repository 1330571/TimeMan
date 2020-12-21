package com.xys.timemgr.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xys.timemgr.entity.User;
import com.xys.timemgr.service.impl.UserServiceImpl;
import com.xys.timemgr.utils.DataConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
        if (_user != null && user.getPassword().equals(_user.getPassword())) {
            session.setAttribute("loginUser", _user.getId().toString());
            return _user.getId().toString();
        } else
            return "Error";
    }

    @GetMapping("/query/{id}")
    public User query(@PathVariable("id") Integer id) {
        System.out.println("Query ... " + id);
        return userService.getOne(new QueryWrapper<User>().eq("id", id));
    }

    @GetMapping("/getTaskIds/{id}")
    public String[] getTaskIds(@PathVariable("id") Integer id) {
        System.out.println("Query All Tasks ..." + id);
        return DataConvert.splitString(userService.getOne(new QueryWrapper<User>().eq("id", id)).getTasks());
    }
}
