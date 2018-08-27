package com.liubarska.contactbook.model.dao;

import com.liubarska.contactbook.model.contact.Contact;

import java.util.List;

/**
 * Created by Iryna on 02.08.2018.
 */
public interface ContactDao extends Dao<Contact> {

    List<Contact> getByPersonId(Integer personId);

    void deleteByPersonId(Integer personId);
}
