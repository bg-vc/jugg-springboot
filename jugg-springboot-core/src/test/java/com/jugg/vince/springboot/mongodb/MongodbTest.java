package com.jugg.vince.springboot.mongodb;

import com.alibaba.fastjson.JSONObject;
import com.jugg.vince.springboot.mongodb.model.Account;
import com.jugg.vince.springboot.mongodb.service.AccountRepository;
import com.jugg.vince.springboot.mongodb.service.IAccountService;
import com.jugg.vince.springboot.test.Tester;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Author:   Vince
 * Date:     2018/7/31 下午4:28
 * Description:
 */
@Slf4j
public class MongodbTest extends Tester {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void saveTest() {
        try {
            Account ac = new Account("1", "王五", 27);
            ac.setAddress("广州市");
            accountService.saveAccount(ac);
            //Account ac = new Account("2", "李四", 20);
            //ac.setAddress("上海市");
            //accountRepository.save(ac);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listTest() {
        try {
            List<Account> list = accountService.listAccount();
            log.info(JSONObject.toJSONString(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByNameTest() {
        try {
            Account ac = accountService.findAccountByName("张三");
            log.info(JSONObject.toJSONString(ac));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateTest() {
        try {
            accountService.updateAccount("张三", "age", 25);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void removeTest() {
        try {
            accountService.removeAccount("王五");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
