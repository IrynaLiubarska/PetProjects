package com.liubarska.db.delete;

import com.liubarska.db.common.DaoRegistry;
import com.liubarska.db.contact.Contact;
import com.liubarska.db.contact.ContactDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Iryna on 09.07.2018.
 */
public class DeleteRestrict implements DeleteStrategy {
    @Autowired
    private DaoRegistry daoRegistry;

    @Override
    public void delete(Integer personId) {
        ContactDao contactDao = (ContactDao) daoRegistry.get(Contact.class);
        List<Contact> contacts = contactDao.getByPersonId(personId);
        if (!contacts.isEmpty()) {
            throw new RuntimeException("This deleteById policy does not allow to deleteById person when contacts still exist");
        }
    }
}
