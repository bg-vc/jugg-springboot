package com.jugg.vince.springboot.elasticsearch.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * Author:   Vince
 * Date:     2018/8/1 上午11:17
 * Description:
 */
@Data
public class EsEntity implements Serializable {
    private static final long serialVersionUID = 5501888368331058030L;

    public static final String INDEX_NAME = "index_entity";

    public static final String TYPE = "tstype";

    private Long id;

    private String name;

    public EsEntity() {
        super();
    }

    public EsEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
