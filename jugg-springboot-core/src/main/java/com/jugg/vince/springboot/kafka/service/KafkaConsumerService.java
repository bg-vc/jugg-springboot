package com.jugg.vince.springboot.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Author:   Vince
 * Date:     2018/8/2 下午4:56
 * Description:
 */
@Component
@Slf4j
public class KafkaConsumerService {
    @KafkaListener(topics = {"springboot-test"})
    public void consumer(String message){
        log.info("springboot-test:{}", message);
    }
}
