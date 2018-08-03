package com.jugg.vince.springboot.mongodb.service;

import com.jugg.vince.springboot.mongodb.model.Account;

import java.util.List;

/**
 * Author:   Vince
 * Date:     2018/7/31 下午3:40
 * Description:
 */
public interface IAccountService {

    void saveAccount(Account account);


    Account findAccountByName(String name);

    void removeAccount(String name);

    void updateAccount(String name, String key, Object value);

    List<Account> listAccount();

}
