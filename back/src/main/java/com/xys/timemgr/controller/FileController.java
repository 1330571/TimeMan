package com.xys.timemgr.controller;

import com.xys.timemgr.entity.UserAvatar;
import com.xys.timemgr.mapper.UserAvatarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Xu Yusheng
 * @since 2020-12-21
 */
@RestController
@RequestMapping("/file")
public class FileController {
    final String filePath = "/Users/xys/picRes/";

    @Autowired
    UserAvatarMapper userAvatarMapper;


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
        String fileName = id.toString() + "avatar.png";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            UserAvatar userAvatar = userAvatarMapper.selectById(id);
            if (userAvatar == null) {
                userAvatar = new UserAvatar();
                userAvatar.setId(id);
                userAvatar.setUri("");
                userAvatarMapper.insert(userAvatar);
            }
            userAvatar.setUri(fileName);
            userAvatarMapper.updateById(userAvatar);
            return "upload success";
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return "upload failed";
    }


    @RequestMapping("/read/{name}")
    public void readFileFromServer(@PathVariable("name") String fileURI, HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = new File(filePath + fileURI);
        FileInputStream fileInputStream = new FileInputStream(file);
        response.setContentType("image/jpeg");
        int sz = fileInputStream.available();
        byte[] srcData = new byte[sz];
        fileInputStream.read(srcData);
        fileInputStream.close();
        OutputStream toClient = response.getOutputStream();
        toClient.write(srcData);
        toClient.close();

    }


}

