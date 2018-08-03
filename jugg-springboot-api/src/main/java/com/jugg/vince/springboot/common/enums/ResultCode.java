package com.jugg.vince.springboot.common.enums;

/**
 * Author:   Vince
 * Date:     2018/7/24 下午6:32
 * Description: 响应码枚举
 */
public enum ResultCode {
    SUCCESS(200, "通讯成功"),
    FAIL(400, "通讯失败"),
    UNAUTHORIZED(401, "认证失败"),
    NOT_FOUND(404, "接口不存在"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误");

    private int code;

    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
