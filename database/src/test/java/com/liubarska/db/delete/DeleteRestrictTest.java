package com.liubarska.db.delete;

import com.liubarska.db.configuration.CommonConfiguration;
import com.liubarska.db.configuration.ContactConfiguration;
import com.liubarska.db.configuration.PersonConfiguration;
import com.liubarska.db.contact.Contact;
import com.liubarska.db.contact.ContactDaoImpl;
import com.liubarska.db.contact.ContactType;
import com.liubarska.db.person.Person;
import com.liubarska.db.person.PersonDaoImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Iryna on 10.07.2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonConfiguration.class, ContactConfiguration.class, PersonConfiguration.class})
public class DeleteRestrictTest {

    private Person firstPerson;
    private Contact firstContact;
    private Contact secondContact;
    @Autowired
    @Qualifier("deleteRestrict")
    private PersonDaoImpl personDao;
    @Autowired
    private ContactDaoImpl contactDao;

    @Before
    public void create() {
        firstPerson = new Person("liubarskyi", "dmytro", 26, "munich");
        firstContact = new Contact(0, ContactType.SKYPE, "ljubarskyj");
        secondContact = new Contact(0, ContactType.VIBER, "ljubarskyj");
    }

    @Before
    public void deleteAllContactRecords() {
        contactDao.deleteAll();
    }

    @Test
    public void shouldNotDeleteFirstPersonBecauseOfExistingContactByRestrictedDeletePolicy() {
        personDao.deleteAll();

        personDao.insert(firstPerson);
        contactDao.insert(firstContact);
        contactDao.insert(secondContact);

        try {
            personDao.deleteById(firstPerson.getId());
            fail();
        } catch (RuntimeException e) {
        }

        assertEquals(new Person(0, "liubarskyi", "dmytro", 26, "munich"), personDao.getById(firstPerson.getId()));
    }

    @Test
    public void shouldDeleteFirstPersonWithNoExistingContactByRestrictedDeletePolicy() {
        personDao.deleteAll();

        personDao.insert(firstPerson);
        personDao.deleteById(firstPerson.getId());

        assertNull(personDao.getById(firstPerson.getId()));
    }

}
