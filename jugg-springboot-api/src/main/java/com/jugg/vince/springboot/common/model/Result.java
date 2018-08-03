package com.jugg.vince.springboot.common.model;

import com.alibaba.fastjson.JSON;
import com.jugg.vince.springboot.common.enums.ResultCode;

/**
 * Author:   Vince
 * Date:     2018/7/24 下午6:49
 * Description:
 */
public class Result<T> {

    private int code;

    private String message;

    private T data;

    public Result() {}

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Result(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    public Result<T> setResult(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        return this;
    }

    public Result<T> setResult(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Result<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
