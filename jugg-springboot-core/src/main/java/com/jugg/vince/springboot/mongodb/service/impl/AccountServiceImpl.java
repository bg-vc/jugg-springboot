package com.jugg.vince.springboot.mongodb.service.impl;

import com.jugg.vince.springboot.demo.model.Users;
import com.jugg.vince.springboot.mongodb.model.Account;
import com.jugg.vince.springboot.mongodb.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author:   Vince
 * Date:     2018/7/31 下午3:44
 * Description:
 */
@Component("accountService")
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private MongoOperations mongoTemplate;

    @Override
    public void saveAccount(Account account) {
        mongoTemplate.save(account);
    }

    @Override
    public Account findAccountByName(String name) {
        return mongoTemplate.findOne(new Query(Criteria.where("name").is(name)), Account.class);
    }

    @Override
    public void removeAccount(String name) {
        mongoTemplate.remove(new Query(Criteria.where("name").is(name)), Account.class);
    }

    @Override
    public void updateAccount(String name, String key, Object value) {
        mongoTemplate.updateFirst(new Query(Criteria.where("name").is(name)),
                Update.update(key, value), Account.class);
    }

    @Override
    public List<Account> listAccount() {
        return mongoTemplate.findAll(Account.class);
    }
}
