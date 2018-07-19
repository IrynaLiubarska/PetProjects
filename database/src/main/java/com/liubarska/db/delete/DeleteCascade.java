package com.liubarska.db.delete;

import com.liubarska.db.contact.ContactDao;

/**
 * Created by Iryna on 09.07.2018.
 */
public class DeleteCascade implements DeleteStrategy {

    private ContactDao contactDao;

    public DeleteCascade(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public void delete(Integer personId) {
        contactDao.deleteByPersonId(personId);
    }
}
