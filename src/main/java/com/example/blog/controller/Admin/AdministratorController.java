package com.example.blog.controller.Admin;


import com.example.blog.po.Result;
import com.example.blog.po.StatusCode;
import com.example.blog.po.User;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdministratorController {

    @Autowired
    private UserService userService;

    @PostMapping("/setAvatar")
    public Result setAvatar(@RequestBody Map<String, Object> para) {
        System.out.println(para);

        String picUrl = (String) para.get("pic_url");
        long id = Long.parseLong(para.get("user_id").toString());
        User admin = userService.findUserById(id);
        System.out.println(admin);
        if (admin == null) {
            return new Result(true, StatusCode.ERROR, "用户不存在", null);
        }
        admin.setAvatar(picUrl);
        System.out.println(admin);
        userService.updateUser(id, admin);
        admin.setPassword(null);

        return new Result(true, StatusCode.OK, "新增成功", admin);
    }

    @PostMapping("/user")
    public Result user(@RequestBody Map<String, User> para) {
        User user = para.get("user");
        User u;
        if (user.getId() == null) {
            u = userService.save(user);
        } else {
            System.out.println(user.getNickname() + " : " + user.getType());
            u = userService.updateUser(user.getId(), user);
        }
        return new Result(true, StatusCode.OK, "修改用户信息成功", u);
    }

    @GetMapping("/users/{id}/delete")
    public Result delete(@PathVariable long id) {
        userService.deleteUserById(id);
        return new Result(true, StatusCode.OK, "删除用户信息成功", null);
    }
}
