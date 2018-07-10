package databaseOrganizer.contact;

import databaseOrganizer.delete.DeleteFactory;
import databaseOrganizer.delete.DeletePolicy;
import databaseOrganizer.delete.Deleter;
import databaseOrganizer.person.PersonDaoImpl;
import lombok.NonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Iryna on 04.07.2018.
 */
public class ContactDaoImpl implements ContactDao {

    private PersonDaoImpl personDao;
    private ContactFileManager contactFileManager = new ContactFileManager();
    private ContactSerializer contactSerializer = new ContactSerializer();
    private ContactDeserializer contactDeserializer = new ContactDeserializer();
    private static int counter = 0;
    private Deleter deleter;

    public ContactDaoImpl(DeletePolicy deletePolicy) {
        personDao = new PersonDaoImpl(deletePolicy);
        DeleteFactory deleteFactory = new DeleteFactory();
        deleter = deleteFactory.createDeleter(deletePolicy);
    }

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
    public void removeAll() {
        try {
            contactFileManager.makeEmpty();
        } catch (IOException e) {
            throw new RuntimeException("Failed to remove all elements");
        }
    }

    @Override
    public void deleteById(Integer id) {
        
    }

    @Override
    public void deleteByPersonId(Integer personId) {

    }

    private String createTransactionLine(Contact contact) {
        contact.setId(counter++);
        return contactSerializer.serialize(contact);
    }
}
