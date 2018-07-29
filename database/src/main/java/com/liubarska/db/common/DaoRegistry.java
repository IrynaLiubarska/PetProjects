package com.liubarska.db.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Iryna on 29.07.2018.
 */

public class DaoRegistry {

    private Map<Class, Dao> classToDao = new HashMap<>();

    public void put(Class value, Dao dao){
        classToDao.put(value, dao);
    }
    
    public Dao get(Class value){
        return classToDao.get(value);
    }
}
