package com.example.blog.common.lang;


import com.example.blog.common.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;


@Data
public class Result implements Serializable {
    public int code;
    public String msg;
    public Object data;
    public Result() {}

    private Result(Object data, String msg, int code) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success() {
        Result result = new Result(null, ResultEnum.SUCCESS.getMessage(), ResultEnum.SUCCESS.getCode());
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result(data, ResultEnum.SUCCESS.getMessage(), ResultEnum.SUCCESS.getCode());
        return result;
    }

    public static Result fail(ResultEnum resultEnum) {
        Result result = new Result(null, resultEnum.getMessage(), resultEnum.getCode());
        return result;
    }
    public static Result fail(String msg) {
        Result result = new Result(null, msg, ResultEnum.ERROR.getCode());
        return result;
    }


    public static Result fail() {
        Result result = new Result(null, ResultEnum.ERROR.getMessage(), ResultEnum.ERROR.getCode());
        return result;
    }
}
