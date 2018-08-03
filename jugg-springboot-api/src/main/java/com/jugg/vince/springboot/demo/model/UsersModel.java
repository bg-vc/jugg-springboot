package com.jugg.vince.springboot.demo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Author:   Vince
 * Date:     2018/7/28 下午9:10
 * Description:
 */
@Data
public class UsersModel implements Serializable {

    private static final long serialVersionUID = -3213615209583704343L;

    private Integer id;

    private String username;

    private String password;

    private Date createTime;

    private Date updateTime;
}
