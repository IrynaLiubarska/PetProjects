package com.liubarska.contactbook.model.dao;

/**
 * Created by Iryna on 02.08.2018.
 */
public interface Dao<T> {

    void insert(T object);

    T getById(Integer id);

    void deleteById(Integer id);

    void deleteAll();
}

