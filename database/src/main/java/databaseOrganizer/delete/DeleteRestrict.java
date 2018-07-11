package databaseOrganizer.delete;

import databaseOrganizer.contact.Contact;
import databaseOrganizer.contact.ContactDao;

import java.util.List;

/**
 * Created by Iryna on 09.07.2018.
 */
public class DeleteRestrict implements Deleter {

    private ContactDao contactDao;

    public DeleteRestrict(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public void delete(Integer personId) {
        List<Contact> contactList = contactDao.getByPersonId(personId);
        if (!contactList.isEmpty()) {
            throw new RuntimeException("This delete policy does not allow to delete person when contacts still exist");
        }
    }

}
