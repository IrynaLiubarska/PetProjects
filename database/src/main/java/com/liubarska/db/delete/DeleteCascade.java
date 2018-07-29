package com.liubarska.db.delete;

import com.liubarska.db.common.DaoRegistry;
import com.liubarska.db.contact.Contact;
import com.liubarska.db.contact.ContactDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Iryna on 09.07.2018.
 */
public class DeleteCascade implements DeleteStrategy {
    @Autowired
    private DaoRegistry daoRegistry;

    @Override
    public void delete(Integer personId) {
        ContactDao contactDao = (ContactDao) daoRegistry.get(Contact.class);
        contactDao.deleteByPersonId(personId);
    }
}
