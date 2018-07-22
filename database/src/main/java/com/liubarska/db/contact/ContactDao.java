package com.liubarska.db.contact;

import com.liubarska.db.common.Dao;

import java.util.List;

/**
 * Created by Iryna on 03.07.2018.
 */
public interface ContactDao extends Dao<Contact> {

    List<Contact> getByPersonId(Integer personId);

    void deleteByPersonId(Integer personId);
}
