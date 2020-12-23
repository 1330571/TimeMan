package com.xys.timemgr.controller;


import com.xys.timemgr.entity.Scheme;
import com.xys.timemgr.mapper.SchemeMapper;
import com.xys.timemgr.utils.DataConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Xu Yusheng
 * @since 2020-12-17
 */
@RestController
@RequestMapping("/scheme")
public class SchemeController {
    @Autowired
    SchemeMapper schemeMapper;

    @GetMapping("/get/{id}")
    public String[] getTags(@PathVariable("id") Integer id) {
        return DataConvert.splitString(schemeMapper.selectById(id).getTagList());
    }

    @GetMapping("/create/{name}")
    public String createTags(@PathVariable("name") String name) {
        Scheme scheme = new Scheme();
        scheme.setSchemeName(name);
        scheme.setTagList("");
        schemeMapper.insert(scheme);
        return "OK";
    }
}
