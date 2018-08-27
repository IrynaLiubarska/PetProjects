package com.liubarska.contactbook.contactTest;

import com.liubarska.contactbook.model.contact.Contact;
import com.liubarska.contactbook.model.contact.ContactConfiguration;
import com.liubarska.contactbook.model.contact.ContactType;
import com.liubarska.contactbook.model.dao.ContactDao;
import com.liubarska.contactbook.model.dao.PersonDao;
import com.liubarska.contactbook.model.person.Person;
import com.liubarska.contactbook.model.person.PersonConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Iryna on 14.08.2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ContactConfiguration.class, PersonConfiguration.class})
public class ContactDaoTest {
    
    @Autowired
    private ContactDao contactDao;
    @Autowired
    private PersonDao personDao;

    private Person firstPerson = new Person("Dmytro", "Liubarskyi", 26, "Munich");
    private Contact firstContact = new Contact(firstPerson, ContactType.SKYPE, "Liubarskyi");


    @Test
    public void shouldInsertContactToDatabase() {
        contactDao.insert(firstContact);

        assertEquals(firstContact, contactDao.getById(firstContact.getId()));
    }

    @Test
    public void shouldDeleteAllContactFromDatabase() {
        contactDao.insert(firstContact);

        contactDao.deleteAll();

        assertNull(contactDao.getById(firstContact.getId()));
    }

    @Test
    public void shouldGetContactByPersonId() {
        contactDao.insert(firstContact);

        assertEquals(singletonList(firstContact), contactDao.getByPersonId(firstPerson.getId()));
    }

    @Test
    public void shouldDeleteContactsByPersonId() {
        contactDao.insert(firstContact);

        contactDao.deleteByPersonId(firstPerson.getId());

        assertEquals(emptyList(), contactDao.getByPersonId(firstPerson.getId()));
    }

}
