package com.jugg.vince.springboot.mongodb.model;

import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Author:   Vince
 * Date:     2018/7/31 下午3:26
 * Description:
 */
@Document(collection = "account")
@CompoundIndexes({
        @CompoundIndex(name = "age_idx", def = "{'name': 1, 'age': -1}")
})
@Data
public class Account implements Serializable {
    private static final long serialVersionUID = 6795963978617574849L;
    @Indexed
    private String uid;
    private String name;
    private int age;
    @Transient
    private String address;

    public Account(String uid, String name, int age) {
        super();
        this.uid = uid;
        this.name = name;
        this.age = age;
    }

}
