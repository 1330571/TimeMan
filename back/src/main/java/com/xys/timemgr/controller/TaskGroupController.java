package com.xys.timemgr.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xys.timemgr.entity.TaskGroup;
import com.xys.timemgr.entity.User;
import com.xys.timemgr.mapper.TaskGroupMapper;
import com.xys.timemgr.mapper.UserMapper;
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
@RequestMapping("/task-group")
public class TaskGroupController {
    @Autowired
    TaskGroupMapper taskGroupMapper;

    @PostMapping("/AllGroup")
    public List<TaskGroup> getGroup(@RequestBody StringWrapper stringWrapper) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("All Group " + stringWrapper.toString());
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (String groupStr : stringWrapper.getData()) {
            arrayList.add(Integer.parseInt(groupStr));
        }
        return taskGroupMapper.selectBatchIds(arrayList);
    }

    @Autowired
    UserMapper userMapper;

    @GetMapping("/joinGroup/{id}/{groupCode}")
    public String joinGroup(@PathVariable("id") Integer id, @PathVariable Integer groupCode) {
        System.out.println(id + " Join " + groupCode);
        TaskGroup taskGroup = taskGroupMapper.selectOne(new QueryWrapper<TaskGroup>().eq("link_code", groupCode));
        if (taskGroup == null) return "invalid Join Code";
        String[] str = DataConvert.splitString(taskGroup.getMemberList());
        boolean alreadyIn = false;
        for (String s : str) {
            if (s.equals(id.toString())) {
                alreadyIn = true;
                break;
            }
        }
        if (alreadyIn) return "you are already in this group";
        String[] newStr = new String[str.length + 1];
        System.arraycopy(str, 0, newStr, 0, str.length);
        newStr[str.length] = id.toString();
        taskGroup.setMemberList(DataConvert.concatString(newStr));
        taskGroupMapper.updateById(taskGroup);
        //then add group to this user
        User user = userMapper.selectById(id);
        String groups = user.getUserGroups();
        String[] groupArr = DataConvert.splitString(groups);
        String[] temp = new String[groupArr.length + 1];
        System.arraycopy(groupArr, 0, temp, 0, groupArr.length);
        temp[groupArr.length] = taskGroup.getId().toString();
        groups = DataConvert.concatString(temp);
        user.setUserGroups(groups);
        userMapper.updateById(user);
        return "success";
    }
}

