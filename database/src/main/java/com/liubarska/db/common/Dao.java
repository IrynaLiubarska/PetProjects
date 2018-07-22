package com.liubarska.db.common;

/**
 * Created by Iryna on 22.07.2018.
 */
public interface Dao<T> {

    void insert(T object);

    T getById(Integer id);

    void deleteById(Integer id);

    void deleteAll();
}
