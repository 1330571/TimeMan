package com.xys.timemgr.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Xu Yusheng
 * @since 2020-12-21
 */
@RestController
@RequestMapping("/file")
public class FileController {
    final String filePath = "/Users/xys/picRes/";

    @PostMapping("/pic")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file, @RequestParam("user") Integer id) {
        System.out.println("ID From => " + id);
        System.out.println("file upload!");
        if (file.isEmpty()) {
            return "error";
        }
        System.out.println("file is not empty");

//        String fileName = file.getOriginalFilename();
        String fileName = id.toString() + "avatar" + file.getContentType();
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            return "upload success";
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return "upload failed";
    }
}
