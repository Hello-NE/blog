package com.example.blog.controller.Admin;


import com.alibaba.fastjson.JSONObject;
import com.example.blog.po.Result;
import com.example.blog.po.StatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@CrossOrigin
public class PictureController {

    // 设置文件保存的根目录，可以是 C 盘或者 D 盘的某个文件夹
    private static final String FILE_UPLOAD_DIRECTORY = "D:\\uploads\\";  // 你可以修改为 D 盘或其他路径

    @PostMapping(value = "/upload")
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new Result(true, StatusCode.ERROR, "上传图片为空", null);
        }
        JSONObject postData = new JSONObject();
        String fileName = file.getOriginalFilename();  // 获取原文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 获取文件后缀
        fileName = UUID.randomUUID() + suffixName;  // 生成唯一的文件名

        // 设定文件保存路径
        File destinationFile = new File(FILE_UPLOAD_DIRECTORY + fileName);

        try {
            // 如果上传的目录不存在，则创建目录
            File dir = new File(FILE_UPLOAD_DIRECTORY);
            if (!dir.exists()) {
                dir.mkdirs();  // 创建目录
            }

            // 将文件保存到指定位置
//            byte[] fileBytes = file.getBytes();
            file.transferTo(destinationFile);
//            postData.put("fileName",fileName);
//            postData.put("data", fileBytes);
//            RestTemplate restTemplate = new RestTemplate();
            // 文件上传成功
//            JSONObject result = restTemplate.postForObject()
            return new Result(true, StatusCode.OK, "上传图片成功", destinationFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(true, StatusCode.ERROR, "上传图片失败", null);
        }
    }
}

