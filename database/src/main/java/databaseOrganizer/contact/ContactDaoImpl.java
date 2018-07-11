package databaseOrganizer.contact;

import databaseOrganizer.person.PersonDao;
import lombok.NonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Iryna on 04.07.2018.
 */
public class ContactDaoImpl implements ContactDao {

    private PersonDao personDao;

    private ContactFileManager contactFileManager = new ContactFileManager();
    private ContactSerializer contactSerializer = new ContactSerializer();
    private ContactDeserializer contactDeserializer = new ContactDeserializer();
    private static int counter = 0;
    @Override
    public void insert(@NonNull Contact contact) {
        Integer personId = contact.getPersonId();
        try {
            personDao.getById(personId);
            String record = createTransactionLine(contact);
            try {
                contactFileManager.writeToFile(record);
            } catch (IOException e) {
                throw new RuntimeException("Failed to insert");
            }
        } catch (Exception e) {
            throw new NoSuchElementException("Failed to insert contact because no such person is exist");
        }
    }

    @Override
    public List<Contact> getByPersonId(@NonNull Integer id) {
        List<Contact> contactList = new ArrayList<>();
        List<String> transactionLine = contactFileManager.readByPersonId(Integer.toString(id));
        if (transactionLine.isEmpty()) {
            throw new RuntimeException();
        }
        for (String str : transactionLine) {
            Contact contact = contactDeserializer.deserialize(str);
            contactList.add(contact);
        }
        return contactList;
    }

    @Override
    public void deleteAll() {
        try {
            contactFileManager.makeEmpty();
        } catch (IOException e) {
            throw new RuntimeException("Failed to remove all elements");
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            try {
                contactFileManager.readById(id);
            } catch (IOException e) {
                throw new RuntimeException(id + "Failed to find this contact id");
            }
            contactFileManager.writeToFile(Integer.toString(id) + ", DELETE");
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete contact");
        }
    }

    @Override
    public void deleteByPersonId(Integer personId) {
        List<Contact> contactList = getByPersonId(personId);
        if (!contactList.isEmpty()) {
            for (Contact contact : contactList) {
                deleteById(contact.getId());
            }
        }
    }

    private String createTransactionLine(Contact contact) {
        contact.setId(counter++);
        return contactSerializer.serialize(contact);
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }
}
