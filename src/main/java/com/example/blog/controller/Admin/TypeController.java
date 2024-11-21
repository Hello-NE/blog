package com.example.blog.controller.Admin;


import com.example.blog.po.Result;
import com.example.blog.po.StatusCode;
import com.example.blog.po.Type;
import com.example.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admin")
@CrossOrigin
public class TypeController {

    @Autowired
    private TypeService typeService;

    //    新增或删除type
    @PostMapping("/types")
    public Result post(@RequestBody Map<String, Type> para) {
        Type type = para.get("type");
        System.out.println("收到" + type);
        if (type.getId() == null) {
            Type type1 = typeService.getTypeByName(para.get("type").getName());
            if (type1 != null) {
                return new Result(false, StatusCode.ERROR, "不能添加重复的分类", null);
            }
            typeService.saveType(type);
        } else {
            System.out.println("修改");
            Type t = typeService.updateType(type.getId(), type);
            if (t == null) {
                return new Result(false, StatusCode.ERROR, "修改失败", null);
            }
            List<Type> typeList = typeService.listByNameExceptSelf(type.getId(), type.getName());
            if (typeList.size() > 0) {
                return new Result(false, StatusCode.ERROR, "分类名称已存在", null);
            }
        }


        return new Result(true, StatusCode.OK, "修改成功", null);
    }


    @GetMapping("/types/{id}/delete")
    public Result delete(@PathVariable Long id) {
        typeService.deleteType(id);
        return new Result(true, StatusCode.OK, "删除成功", null);
    }

}