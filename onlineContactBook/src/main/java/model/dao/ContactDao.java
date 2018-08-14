package model.dao;

import model.Contact;

import java.util.List;

/**
 * Created by Iryna on 02.08.2018.
 */
public interface ContactDao extends Dao<Contact> {

    List<Contact> getByPersonId(Integer personId);

    void deleteByPersonId(Integer personId);
}
