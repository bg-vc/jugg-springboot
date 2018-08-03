package com.jugg.vince.springboot.elasticsearch.service.impl;

import com.jugg.vince.springboot.elasticsearch.model.EsEntity;
import com.jugg.vince.springboot.elasticsearch.service.EsEntityService;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Author:   Vince
 * Date:     2018/8/1 上午11:29
 * Description:
 */
@Service("esEntityService")
@Slf4j
public class EsEntityServiceImpl implements EsEntityService {
    @Autowired
    private JestClient jestClient;

    @Override
    public void saveEsEntity(EsEntity esEntity) {
        Index index = new Index.Builder(esEntity).index(EsEntity.INDEX_NAME).type(EsEntity.TYPE).build();
        try {
            jestClient.execute(index);
            log.info("ES save success");
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Override
    public void saveEsEntity(List<EsEntity> esEntityList) {
        Bulk.Builder bulk = new Bulk.Builder();
        for(EsEntity esEntity : esEntityList) {
            Index index = new Index.Builder(esEntity).index(EsEntity.INDEX_NAME).type(EsEntity.TYPE).build();
            bulk.addAction(index);
        }
        try {
            jestClient.execute(bulk.build());
            log.info("ES save success");
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Override
    public List<EsEntity> searchEsEntity(String searchContent) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //searchSourceBuilder.query(QueryBuilders.queryStringQuery(searchContent));
        //searchSourceBuilder.field("name");
        searchSourceBuilder.query(QueryBuilders.matchQuery("name",searchContent));
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(EsEntity.INDEX_NAME).addType(EsEntity.TYPE).build();
        try {
            JestResult result = jestClient.execute(search);
            return result.getSourceAsObjectList(EsEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }

}
