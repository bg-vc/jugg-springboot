package com.jugg.vince.springboot.demo.service.impl;

import com.jugg.vince.springboot.demo.dao.UsersMapper;
import com.jugg.vince.springboot.demo.model.Users;
import com.jugg.vince.springboot.demo.service.UsersService;
import com.jugg.vince.springboot.common.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/07/26.
 */
@Service
@Transactional
public class UsersServiceImpl extends AbstractService<Users> implements UsersService {
    @Resource
    private UsersMapper tUsersMapper;

}
