package com.jugg.vince.springboot.common.core;

/**
 * Author:   Vince
 * Date:     2018/7/24 下午7:07
 * Description:
 */
public class ServiceException extends RuntimeException {

    public ServiceException() {}

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
