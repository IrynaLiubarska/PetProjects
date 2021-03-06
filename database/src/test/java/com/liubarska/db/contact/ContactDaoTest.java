package com.liubarska.db.contact;

import com.liubarska.db.configuration.CommonConfiguration;
import com.liubarska.db.configuration.ContactConfiguration;
import com.liubarska.db.configuration.PersonConfiguration;
import com.liubarska.db.person.Person;
import com.liubarska.db.person.PersonDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

/**
 * Created by Iryna on 06.07.2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes ={CommonConfiguration.class, ContactConfiguration.class, PersonConfiguration.class})
    public class ContactDaoTest {

    @Autowired
    private ContactDao contactDao;
    @Autowired
    private PersonDao personDao;

    private Person firstPerson;
    private Contact firstContact;
    private Contact secondContact;
    private Contact firstContactExpected;
    private Contact secondContactExpected;

    @Before
    public void removeAllRecords() {
        prepareDao();
        prepareTestData();
    }

    private void prepareDao() {
        personDao.deleteAll();
        contactDao.deleteAll();
    }

    private void prepareTestData() {
        firstPerson = new Person("liubarskyi", "dmytro", 26, "munich");
        firstContact = new Contact(0, ContactType.SKYPE, "ljubarskyj");
        secondContact = new Contact(0, ContactType.VIBER, "ljubarskyj");
        firstContactExpected = new Contact(0, 0, ContactType.SKYPE, "ljubarskyj");
        secondContactExpected = new Contact(1, 0, ContactType.VIBER, "ljubarskyj");
    }

    @Test
    public void shouldInsertContactToDatabase() {
        personDao.insert(firstPerson);
        contactDao.insert(firstContact);

        assertEquals(singletonList(firstContactExpected), contactDao.getByPersonId(firstPerson.getId()));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenInsertingNull() {
        contactDao.insert(null);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenGetNullId() {
        contactDao.getById(null);
    }

    @Test
    public void shouldGetContactById() {
        personDao.insert(firstPerson);
        contactDao.insert(firstContact);

        assertEquals(firstContactExpected, contactDao.getById(firstContact.getId()));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenGetNullPersonId() {
        contactDao.getByPersonId(null);
    }

    @Test
    public void shouldGetListOfContactsByPersonId() {
        personDao.insert(firstPerson);
        contactDao.insert(firstContact);
        contactDao.insert(secondContact);

        assertEquals(asList(firstContactExpected, secondContactExpected), contactDao.getByPersonId(firstPerson.getId()));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionByInsertionWhenPersonDoesNotExists() {
        contactDao.insert(firstContact);
    }

    @Test
    public void shouldDeleteContact() {
        personDao.insert(firstPerson);
        contactDao.insert(firstContact);

        contactDao.deleteById(firstContact.getId());

        assertEquals(emptyList(), contactDao.getByPersonId(firstPerson.getId()));
    }

    @Test
    public void shouldReturnNullWhenContactIsDeleted() {
        personDao.insert(firstPerson);
        contactDao.insert(firstContact);

        contactDao.deleteById(firstContact.getId());

        assertNull(contactDao.getById(firstContact.getId()));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenDeleteNullId() {
        contactDao.deleteById(null);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenDeleteIdThatIsNotPresentInDatabase() {
        contactDao.deleteById(firstContact.getId());
    }
}

