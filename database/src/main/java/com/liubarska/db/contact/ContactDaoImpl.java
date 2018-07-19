package com.liubarska.db.contact;

import com.liubarska.db.person.PersonDao;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iryna on 04.07.2018.
 */
public class ContactDaoImpl implements ContactDao {

    private PersonDao personDao;

    private ContactFileManager contactFileManager = new ContactFileManager();
    private ContactSerializer contactSerializer = new ContactSerializer();
    private ContactDeserializer contactDeserializer = new ContactDeserializer();
    private static Integer currentId;

    public ContactDaoImpl() {
        currentId = contactFileManager.readLargestId() + 1;
    }

    @Override
    public void insert(@NonNull Contact contact) {
        Integer personId = contact.getPersonId();
        if (personDao.getById(personId) == null) {
            throw new RuntimeException("there is no person with this id");
        }
        String record = createRecord(contact);
        contactFileManager.writeToFile(record);
    }

    @Override
    public List<Contact> getByPersonId(@NonNull Integer id) {
        List<Contact> contacts = new ArrayList<>();
        List<String> records = contactFileManager.readByPersonId(Integer.toString(id));
        if (records.isEmpty()) {
            return contacts;
        }
        for (String record : records) {
            Contact contact = contactDeserializer.deserialize(record);
            contacts.add(contact);
        }
        return contacts;
    }

    @Override
    public void deleteAll() {
        contactFileManager.makeEmpty();
        currentId = 0;
    }

    @Override
    public void deleteById(@NonNull Integer id) {
        contactFileManager.deleteFromFile(id, contactDeserializer);
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

    private String createRecord(Contact contact) {
        contact.setId(currentId++);
        return contactSerializer.serialize(contact);
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }
}
