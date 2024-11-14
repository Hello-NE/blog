package com.example.blog.controller.Admin;


import com.example.blog.po.Essay;
import com.example.blog.po.Result;
import com.example.blog.po.StatusCode;
import com.example.blog.service.EssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class EssayController {
    @Autowired
    private EssayService essayService;

    @GetMapping("/essay/{id}/delete")
    public Result delete(@PathVariable Long id) {
        essayService.deleteEssay(id);
        return new Result(true, StatusCode.OK, "删除随笔成功", null);
    }

    @PostMapping("/essay")
    public Result post(@RequestBody Map<String, Essay> para) {
        System.out.println(para);
        Essay essay = para.get("essay");
        System.out.println(essay);
        Essay e;
        if (essay.getId() == null) {
            e = essayService.saveEssay(essay);
        } else {
            e = essayService.updateEssay(essay.getId(), essay);
        }
        if (e == null) {
            return new Result(false, StatusCode.ERROR, "操作失败", null);

        }
        return new Result(true, StatusCode.OK, "操作成功", null);

    }

    @GetMapping("/essays")
    public Result essays() {
        return new Result(true, StatusCode.OK, "获取随笔列表成功", essayService.listEssay());
    }
}
