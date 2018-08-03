package com.jugg.vince.springboot.common.core;

import com.jugg.vince.springboot.common.enums.ResultCode;
import com.jugg.vince.springboot.common.model.Result;

/**
 * Author:   Vince
 * Date:     2018/7/24 下午6:48
 * Description:
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result<String> genSuccessResult() {
        return new Result<String>()
                .setCode(ResultCode.SUCCESS.getCode())
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static Result<Object> genSuccessResult(Object data) {
        return new Result<Object>()
                .setCode(ResultCode.SUCCESS.getCode())
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result<String> genFailResult(String message) {
        return new Result<String>()
                .setCode(ResultCode.FAIL.getCode())
                .setMessage(message);
    }
}
