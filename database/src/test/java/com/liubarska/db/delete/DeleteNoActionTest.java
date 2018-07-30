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

/**
 * Created by Iryna on 29.07.2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonConfiguration.class, ContactConfiguration.class, PersonConfiguration.class})
public class DeleteNoActionTest {
    private Person firstPerson;
    private Contact firstContact;
    private Contact secondContact;
    @Autowired
    private ContactDaoImpl contactDao;
    @Autowired
    @Qualifier("deleteNoAction")
    private PersonDaoImpl personDao;

    @Before
    public void create() {
        firstPerson = new Person("liubarskyi", "dmytro", 26, "munich");
        firstContact = new Contact(0, ContactType.SKYPE, "ljubarskyj");
        secondContact = new Contact(0, ContactType.VIBER, "ljubarskyj");
    }

    @Before
    public void deleteAllContactRecords() {
        contactDao.deleteAll();
        personDao.deleteAll();
    }

    @Test
    public void shouldDeleteFirstPerson() {
        personDao.insert(firstPerson);
        contactDao.insert(firstContact);
        contactDao.insert(secondContact);

        personDao.deleteById(firstPerson.getId());
        assertNull(personDao.getById(firstPerson.getId()));
    }
}
