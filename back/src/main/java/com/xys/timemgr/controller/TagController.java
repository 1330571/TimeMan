package com.xys.timemgr.controller;


import com.xys.timemgr.entity.Tag;
import com.xys.timemgr.mapper.TagMapper;
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
@RequestMapping("/tag")
public class TagController {

    @Autowired
    TagMapper tagMapper;

    @GetMapping("/get/{id}")
    public Tag getTag(@PathVariable("id") Integer id) {
        return tagMapper.selectById(id);
    }

    @GetMapping("/getAll")
    public List<Tag> getAllTag() {
        return tagMapper.selectList(null);
    }

    @PostMapping("/set")
    public void setTag(@RequestBody Tag tag) {
        System.out.println("Tag..." + tag.toString());
        tagMapper.insert(tag);
    }

    @GetMapping("/delete/{id}")
    public void deleteTag(@PathVariable("id") Integer id) {
        tagMapper.deleteById(id);
    }

    @GetMapping("/update")
    public void modifyTag(@RequestBody Tag tag) {
        tagMapper.updateById(tag);
    }

    //http://localhost:8081/tag/da
    @GetMapping("/da")
    public void deleteAll() {
        List<Tag> task = tagMapper.selectList(null);
        for (Tag _task : task) {
            tagMapper.deleteById(_task.getId());
        }
    }
}
