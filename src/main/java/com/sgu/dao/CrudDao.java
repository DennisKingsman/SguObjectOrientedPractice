package com.sgu.dao;

import java.util.List;

public interface CrudDao<T> {

    T getByName(String name);

    List<T> getAll();

    int create(T bean);

    default int delete(T bean) {
        return 0;
    }

    int deleteByName(String name);

}
