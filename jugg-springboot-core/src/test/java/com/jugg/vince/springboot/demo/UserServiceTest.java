package com.jugg.vince.springboot.demo;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.jugg.vince.springboot.demo.model.Users;
import com.jugg.vince.springboot.demo.service.UsersService;
import com.jugg.vince.springboot.test.Tester;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Author:   Vince
 * Date:     2018/7/26 下午9:48
 * Description:
 */
@Slf4j
public class UserServiceTest extends Tester {

    @Resource
    private UsersService usersService;

    @Test
    public void addTest() {
        Users user = new Users();
        user.setUsername("vince");
        user.setPassword("hao234");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        usersService.save(user);
    }

    @Test
    public void updateTest() {
        Users user = new Users();
        user.setId(1);
        user.setUsername("jugg123");
        user.setPassword("hao123");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        usersService.update(user);
    }

    @Test
    public void findByIdTest() {
        Users user = usersService.findById(1);
        log.info(JSONObject.toJSONString(user));
    }

    @Test
    public void listTest() {
        Condition condition = new Condition(Users.class);
        Example.Criteria criteria = condition.createCriteria();
        //criteria.andEqualTo("username", "vince");
        criteria.andLike("username", "%vince%");
        int total = usersService.selectCountByCondition(condition);
        condition.setOrderByClause("id desc");
        PageHelper.startPage(1, 2);
        List<Users> list = usersService.findByCondition(condition);
        log.info("total:{}, list:{}", total, JSONObject.toJSONString(list));
    }

}
