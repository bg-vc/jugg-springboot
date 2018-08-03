package com.jugg.vince.springboot.mongodb.service;

import com.jugg.vince.springboot.mongodb.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Author:   Vince
 * Date:     2018/7/31 下午3:35
 * Description:
 */
public interface AccountRepository extends MongoRepository<Account, Integer> {

}
