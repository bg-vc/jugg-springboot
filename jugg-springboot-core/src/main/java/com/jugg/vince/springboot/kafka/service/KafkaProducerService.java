package com.jugg.vince.springboot.kafka.service;

import com.alibaba.fastjson.JSON;
import com.jugg.vince.springboot.common.core.ResultGenerator;
import com.jugg.vince.springboot.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Author:   Vince
 * Date:     2018/8/2 下午4:52
 * Description:
 */
@Component
@Slf4j
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public Result sendMessage(String topic, Object obj) {
        try {
            String data = JSON.toJSONString(obj);
            log.info("topic:{}, data:{}", topic, data);
            kafkaTemplate.send(topic, data);
            log.info("kafka send msg success");
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("kafka send msg error:{}", e.getMessage());
        }
        return ResultGenerator.genFailResult("send msg error");
    }
}
