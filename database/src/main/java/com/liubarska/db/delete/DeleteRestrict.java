package com.liubarska.db.delete;

import com.liubarska.db.contact.Contact;
import com.liubarska.db.contact.ContactDao;

import java.util.List;

/**
 * Created by Iryna on 09.07.2018.
 */
public class DeleteRestrict implements DeleteStrategy {

    private ContactDao contactDao;

    public DeleteRestrict(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public void delete(Integer personId) {
        List<Contact> contacts = contactDao.getByPersonId(personId);
        if (!contacts.isEmpty()) {
            throw new RuntimeException("This deleteById policy does not allow to deleteById person when contacts still exist");
        }
    }
}
