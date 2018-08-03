package com.jugg.vince.springboot.web.elasticsearch;

import com.jugg.vince.springboot.common.model.Result;
import com.jugg.vince.springboot.demo.model.Users;
import com.jugg.vince.springboot.elasticsearch.model.EsEntity;
import com.jugg.vince.springboot.elasticsearch.service.EsEntityService;
import com.jugg.vince.springboot.kafka.service.KafkaProducerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author:   Vince
 * Date:     2018/8/1 下午12:21
 * Description:
 */
@RestController
public class EsEntityController {
    @Autowired
    private EsEntityService esEntityService;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @RequestMapping(value="/api/es/save", method=RequestMethod.POST, produces="application/json")
    public String save(long id, String name) {
        if(id>0 && StringUtils.isNotEmpty(name)) {
            EsEntity esEntity = new EsEntity(id,name);
            List<EsEntity> addList = new ArrayList<>();
            addList.add(esEntity);
            esEntityService.saveEsEntity(addList);
            return "OK";
        }else {
            return "bad input value";
        }
    }

    @RequestMapping(value="api/es/search", method=RequestMethod.POST, produces="application/json")
    public List<EsEntity> search(String name) {
        List<EsEntity> list = null;
        if(StringUtils.isNotEmpty(name)) {
            list = esEntityService.searchEsEntity(name);
        }
        return list;
    }

    @RequestMapping(value = "api/kafka", method=RequestMethod.GET)
    public Result kafka() {
        Users user1 = new Users();
        user1.setId(1);
        user1.setUsername("test1");
        user1.setPassword("test1");
        user1.setCreateTime(new Date());
        user1.setUpdateTime(new Date());
        Result result = kafkaProducerService.sendMessage("springboot-test", user1);
        return result;
    }

}
