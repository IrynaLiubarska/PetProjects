package databaseOrganizer.delete;

import databaseOrganizer.contact.ContactDao;

/**
 * Created by Iryna on 09.07.2018.
 */
public class DeleteCascade implements Deleter {
    
    private ContactDao contactDao;

    public DeleteCascade(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public void delete(Integer personId) {
        contactDao.getByPersonId(personId);
    }
}
