package com.sgu.dao;

import com.sgu.bean.Client;
import com.sgu.bean.CreditType;

import java.util.ArrayList;
import java.util.List;

public interface CrudDependentDao<T> {

    T getByName(String name);

    List<T> getAll();

    default List<T> getByForeignKey(String key, Object object){
        return new ArrayList<>();
    }

    int createDependentBean(T bean, String responsibleClient, String creditTypeName);

    default int delete(T bean){
        return 0;
    }

    int deleteByName(String name);

}
