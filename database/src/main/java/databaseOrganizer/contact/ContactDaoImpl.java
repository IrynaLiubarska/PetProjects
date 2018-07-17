package databaseOrganizer.contact;

import databaseOrganizer.person.PersonDao;
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
        personDao.getById(personId);
        String record = createTransactionLine(contact);
        contactFileManager.writeToFile(record);
    }

    @Override
    public List<Contact> getByPersonId(@NonNull Integer id) {
        List<Contact> contactList = new ArrayList<>();
        List<String> transactionLine = contactFileManager.readByPersonId(Integer.toString(id));
        for (String str : transactionLine) {
            Contact contact = contactDeserializer.deserialize(str);
            contactList.add(contact);
        }
        return contactList;
    }

    @Override
    public void deleteAll() {
        contactFileManager.makeEmpty();
        currentId = 0;
    }

    @Override
    public void deleteById(Integer id) {
        contactFileManager.readById(id);
        contactFileManager.writeToFile(Integer.toString(id) + ", DELETE");
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
        contact.setId(currentId++);
        return contactSerializer.serialize(contact);
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

}
