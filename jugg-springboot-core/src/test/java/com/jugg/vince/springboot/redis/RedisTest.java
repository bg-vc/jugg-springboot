package com.jugg.vince.springboot.redis;

import com.jugg.vince.springboot.common.redis.RedisService;
import com.jugg.vince.springboot.common.util.LockUtil;
import com.jugg.vince.springboot.demo.model.UsersModel;
import com.jugg.vince.springboot.test.Tester;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Author:   Vince
 * Date:     2018/7/28 下午9:43
 * Description:
 */
@Slf4j
public class RedisTest extends Tester {

    @Resource
    private RedisService redisService;

    @Test
    public void lockTest() {
        UsersModel model = new UsersModel();
        model.setId(1);
        UsersModel lock = LockUtil.lockBill(model);
        try {
            if(null == lock) {
                log.info("the current request is handling...");
            } else {
                log.info("the current request has been lock.");
            }
        } finally {
            LockUtil.unLockBill(model);
        }
    }

    @Test
    public void redisTest() {
        boolean setBool = redisService.set("jugg", "vince", 600L);
        log.info("setBool:{}", setBool);
        Object obj = redisService.get("jugg");
        log.info("value:{}", String.valueOf(obj));
    }

}
