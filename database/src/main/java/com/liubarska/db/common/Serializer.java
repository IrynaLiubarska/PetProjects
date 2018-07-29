package com.liubarska.db.common;

/**
 * Created by Iryna on 22.07.2018.
 */
public interface Serializer<T> {
    
    String serialize(T object);
}

