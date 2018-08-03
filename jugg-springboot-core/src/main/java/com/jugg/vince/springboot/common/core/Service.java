package com.jugg.vince.springboot.common.core;

import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * Author:   Vince
 * Date:     2018/7/24 下午6:37
 * Description:
 */
public interface Service<T> {
    /**
     * 保存
     * @param model
     */
    void save(T model);

    /**
     * 批量保存
     * @param models
     */
    void save(List<T> models);

    /**
     * 根据主键删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(String ids);

    /**
     * 更新
     * @param model
     */
    void update(T model);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    T findById(Integer id);

    /**
     * 根据字段查找
     * @param fieldName
     * @param value
     * @throws TooManyResultsException
     */
    T findBy(String fieldName, Object value) throws TooManyResultsException;

    /**
     * 根据多个id查找
     * @param ids
     * @return
     */
    List<T> findByIds(String ids);

    /**
     * 根据条件查找
     * @param condition
     * @return
     */
    List<T> findByCondition(Condition condition);

    /**
     * 查询所有
     * @return
     */
    List<T> findAll();

    /**
     * 根据Codition条件查询总数
     * @param condition
     * @return
     */
    int selectCountByCondition(Condition condition);

}
