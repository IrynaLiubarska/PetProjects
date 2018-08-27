package com.liubarska.contactbook.personTest;

import com.liubarska.contactbook.model.contact.Contact;
import com.liubarska.contactbook.model.contact.ContactConfiguration;
import com.liubarska.contactbook.model.contact.ContactType;
import com.liubarska.contactbook.model.dao.ContactDao;
import com.liubarska.contactbook.model.dao.PersonDao;
import com.liubarska.contactbook.model.person.Person;
import com.liubarska.contactbook.model.person.PersonConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.util.Collections.singletonList;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Iryna on 14.08.2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersonConfiguration.class, ContactConfiguration.class})
public class PersonDaoTest {

    @Autowired
    private PersonDao personDao;
    @Autowired
    private ContactDao contactDao;
    private Person firstPerson;
    private Person secondPerson;
    private Person thirdPerson;

    @Before
    public void before() {
        firstPerson = new Person("Dmytro", "Liubarskyi", 26, "Munich");
        secondPerson = new Person("Iryna", "Liubarska", 29, "Munich");
        thirdPerson = new Person("Kateryna", "Liubarska", 31, "kyiv");
    }

    @Test
    public void shouldInsertPersonInDatabase() {
        personDao.insert(firstPerson);
        Person actual = personDao.getById(firstPerson.getId());
        assertEquals(firstPerson, actual);
        assertEquals(0, actual.getContacts().size());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenInsertingNull() {
        personDao.insert(null);
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenInsertingPersonWithOneNullName() {
        personDao.insert(new Person(null, "Liubarskyi", 26, "Munich"));
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenInsertingPersonWithOneNullLastName() {
        personDao.insert(new Person("Dmytro", null, 26, "Munich"));
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenInsertingPersonWithOneNullAge() {
        personDao.insert(new Person("Dmytro", "Liubarskyi", null, "Munich"));
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenInsertingPersonWithOneNullCity() {
        personDao.insert(new Person("Dmytro", "Liubarskyi", 26, null));
    }

    @Test
    public void shouldGetPersonBySurname() {
        personDao.insert(secondPerson);
        assertEquals(singletonList(secondPerson), personDao.getBySurname(secondPerson.getLastName()));
    }

    @Test
    public void shouldDeleteAll() {
        personDao.deleteAll();
        assertNull(personDao.getById(firstPerson.getId()));
    }

    @Test
    public void shouldDeletePersonById() {
        personDao.insert(thirdPerson);
        personDao.deleteById(thirdPerson.getId());
        assertNull(personDao.getById(thirdPerson.getId()));
    }

    @Test
    public void shouldGetPersonWithAllContacts() {
        Person person = new Person("Dmytro", "Liubarskyi", 26, "Munich");
        Contact contact = new Contact(person, ContactType.SKYPE, "Liubarskyi");
        person.setContacts(singletonList(contact));

        personDao.insert(person);
        Person actual = personDao. getById(person.getId());

        assertEquals(1, actual.getContacts().size());
        assertEquals(contact, actual.getContacts().get(0));
    }
    
}
