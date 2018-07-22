package com.liubarska.db.contact;

import java.util.List;

/**
 * Created by Iryna on 03.07.2018.
 */
public interface ContactDao {

    void insert(Contact contact);
    
    Contact getById(Integer id);

    List<Contact> getByPersonId(Integer personId);

    void deleteAll();

    void deleteById(Integer id);

    void deleteByPersonId(Integer personId);
}
