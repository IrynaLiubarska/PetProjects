package com.liubarska.db.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Iryna on 29.07.2018.
 */

public class DaoRegistry {

    private Map<Class, Dao> classToDao = new HashMap<>();

    public void put(Class type, Dao dao){
        classToDao.put(type, dao);
    }
    
    public Dao get(Class type){
        return classToDao.get(type);
    }
}
