package com.xys.timemgr.controller;


import com.xys.timemgr.entity.Tag;
import com.xys.timemgr.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/set")
    public void setTag(@RequestBody Tag tag) {
        tagMapper.insert(tag);
    }

    @GetMapping("/update")
    public void modifyTag(@RequestBody Tag tag) {
        tagMapper.updateById(tag);
    }
}
