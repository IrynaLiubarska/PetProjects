package databaseOrganizerTest;

import databaseOrganizer.contact.Contact;
import databaseOrganizer.contact.ContactDaoImpl;
import databaseOrganizer.contact.ContactType;
import databaseOrganizer.delete.DeletePolicy;
import databaseOrganizer.person.Person;
import databaseOrganizer.person.PersonDaoImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Iryna on 10.07.2018.
 */
public class DeleterTest {

    private Person firstPerson;
    private Contact firstContact;
    private Contact secondContact;
    private PersonDaoImpl personDao;
    private ContactDaoImpl contactDao;

    @Before
    public void create() {
        firstPerson = new Person("liubarskyi", "dmytro", 26, "munich");
        firstContact = new Contact(0, ContactType.SKYPE, "ljubarskyj");
        secondContact = new Contact(0, ContactType.VIBER, "ljubarskyj");
    }

    @Before
    public void deleteAllContactRecords() {
        contactDao = new ContactDaoImpl();
        contactDao.deleteAll();
    }

    @Test(expected = RuntimeException.class)
    public void shouldDeleteFirstPerson() {
        personDao = new PersonDaoImpl(DeletePolicy.DELETE_NO_ACTION, contactDao);
        contactDao.setPersonDao(personDao);
        personDao.deleteAll();

        personDao.insert(firstPerson);
        contactDao.insert(firstContact);
        contactDao.insert(secondContact);

        personDao.delete(firstPerson.getId());
        personDao.getById(firstPerson.getId());
    }

    @Test
    public void shouldNotDeleteFirstPersonBecauseOfExistingContactByRestrictedDeletePolicy() {
        personDao = new PersonDaoImpl(DeletePolicy.DELETE_RESTRICT, contactDao);
        contactDao.setPersonDao(personDao);
        personDao.deleteAll();

        personDao.insert(firstPerson);
        contactDao.insert(firstContact);
        contactDao.insert(secondContact);

        try {
            personDao.delete(firstPerson.getId());
            fail();
        } catch (RuntimeException e) {
        }

        assertEquals(new Person(0, "liubarskyi", "dmytro", 26, "munich"), personDao.getById(firstPerson.getId()));
    }

    @Test
    public void shouldDeleteFirstPersonWithNoExistingContactByRestrictedDeletePolicy() {
        personDao = new PersonDaoImpl(DeletePolicy.DELETE_RESTRICT, contactDao);
        contactDao.setPersonDao(personDao);
        personDao.deleteAll();

        personDao.insert(firstPerson);
        personDao.delete(firstPerson.getId());
        try {
            personDao.getById(firstPerson.getId());
            fail();
        } catch (RuntimeException e) {
        }
    }

    @Test
    public void shouldDeletePersonAndAllContactsCascade() {
        personDao = new PersonDaoImpl(DeletePolicy.DELETE_CASCADE, contactDao);
        contactDao.setPersonDao(personDao);
        personDao.deleteAll();

        personDao.insert(firstPerson);
        contactDao.insert(firstContact);
        contactDao.insert(secondContact);

        personDao.delete(firstPerson.getId());
        try {
            personDao.getById(firstPerson.getId());
            fail();
        } catch (RuntimeException e) {
        }
        assertEquals(new ArrayList<>(), contactDao.getByPersonId(firstPerson.getId()));
    }
}
