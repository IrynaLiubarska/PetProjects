package com.liubarska.db.contact;

import com.liubarska.db.person.PersonDao;
import lombok.NonNull;

import java.util.List;

/**
 * Created by Iryna on 04.07.2018.
 */
public class ContactDaoImpl implements ContactDao {

    private PersonDao personDao;
    private ContactFileManager contactFileManager = new ContactFileManager();

    @Override
    public void insert(@NonNull Contact contact) {
        Integer personId = contact.getPersonId();
        if (personDao.getById(personId) == null) {
            throw new RuntimeException("There is no person with such id");
        }
        contactFileManager.insert(contact);
    }

    @Override
    public Contact getById(@NonNull Integer id) {
        return contactFileManager.getById(id);
    }

    @Override
    public List<Contact> getByPersonId(@NonNull Integer id) {
        return contactFileManager.getByPersonId(id);
    }

    @Override
    public void deleteAll() {
        contactFileManager.deleteAll();
    }

    @Override
    public void deleteById(@NonNull Integer id) {
        Contact contact = getById(id);
        if (contact == null) {
            throw new RuntimeException("There is no contact with such id");
        }
        contactFileManager.deleteById(id);
    }

    @Override
    public void deleteByPersonId(@NonNull Integer personId) {
        List<Contact> contacts = getByPersonId(personId);
        if (!contacts.isEmpty()) {
            for (Contact contact : contacts) {
                deleteById(contact.getId());
            }
        }
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }
}
