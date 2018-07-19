package com.liubarska.dbTest;

import com.liubarska.db.contact.Contact;
import com.liubarska.db.contact.ContactDaoImpl;
import com.liubarska.db.contact.ContactType;
import com.liubarska.db.delete.DeletePolicy;
import com.liubarska.db.person.Person;
import com.liubarska.db.person.PersonDaoImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.fail;

/**
 * Created by Iryna on 10.07.2018.
 */
public class DeleteStrategyTest {

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

    @Test
    public void shouldDeleteFirstPerson() {
        personDao = new PersonDaoImpl(DeletePolicy.DELETE_NO_ACTION, contactDao);
        contactDao.setPersonDao(personDao);
        personDao.deleteAll();

        personDao.insert(firstPerson);
        contactDao.insert(firstContact);
        contactDao.insert(secondContact);

        personDao.delete(firstPerson.getId());
        assertNull(personDao.getById(firstPerson.getId()));
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
        
        assertNull(personDao.getById(firstPerson.getId()));
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

        assertNull(personDao.getById(firstPerson.getId()));
        assertEquals(new ArrayList<>(), contactDao.getByPersonId(firstPerson.getId()));
    }
}
