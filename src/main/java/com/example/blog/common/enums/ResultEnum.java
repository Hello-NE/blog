package com.example.blog.common.enums;

public enum ResultEnum {

    SUCCESS(200, "操作成功"),
    ERROR(-1, "出现错误"),
    NOT_FOUND(0, "信息未找到"),
    OPERATION_FAILED(-2, "操作失败"),
    Unauthorized(401, "账户未认证");

    final int code;
    final String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
