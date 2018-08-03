package com.jugg.vince.springboot.kafka;

import com.jugg.vince.springboot.common.model.Result;
import com.jugg.vince.springboot.demo.model.Users;
import com.jugg.vince.springboot.kafka.service.KafkaProducerService;
import com.jugg.vince.springboot.test.Tester;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Author:   Vince
 * Date:     2018/8/2 下午4:59
 * Description:
 */
@Slf4j
public class KafkaServiceTest extends Tester {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Test
    public void kafkaProducerTest() {
        Users user1 = new Users();
        user1.setId(1);
        user1.setUsername("test1");
        user1.setPassword("test1");
        user1.setCreateTime(new Date());
        user1.setUpdateTime(new Date());
        Result result = kafkaProducerService.sendMessage("springboot-test", user1);
        log.info("result:{}", result);
    }

}
