package com.jugg.vince.springboot.elasticsearch.service;

import com.jugg.vince.springboot.elasticsearch.model.EsEntity;

import java.util.List;

/**
 * Author:   Vince
 * Date:     2018/8/1 上午11:27
 * Description:
 */
public interface EsEntityService {
    /**
     *
     * @param esEntity
     * @return
     */
    void saveEsEntity(EsEntity esEntity);

    /**
     *
     * @param esEntityList
     */
    void saveEsEntity(List<EsEntity> esEntityList);

    /**
     *
     * @param searchContent
     * @return
     */
    List<EsEntity> searchEsEntity(String searchContent);

}
