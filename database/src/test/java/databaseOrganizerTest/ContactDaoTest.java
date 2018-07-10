package databaseOrganizerTest;

import databaseOrganizer.contact.Contact;
import databaseOrganizer.contact.ContactDao;
import databaseOrganizer.contact.ContactDaoImpl;
import databaseOrganizer.contact.ContactType;
import databaseOrganizer.delete.DeletePolicy;
import databaseOrganizer.person.Person;
import databaseOrganizer.person.PersonDao;
import databaseOrganizer.person.PersonDaoImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static java.util.Collections.singletonList;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by Iryna on 06.07.2018.
 */
public class ContactDaoTest {

    private ContactDao contactDaoImpl = new ContactDaoImpl(DeletePolicy.DELETE_NO_ACTION);
    private PersonDao personDaoImpl = new PersonDaoImpl(DeletePolicy.DELETE_NO_ACTION);
    private Person firstPerson;
    private Contact firstContact;
    private Contact secondContact;
    private Contact firstContactExpected;
    private Contact secondContactExpected;

    @Before
    public void removeAllRecords() throws IOException {
        personDaoImpl.removeAll();
        contactDaoImpl.removeAll();
        firstPerson = new Person("liubarskyi", "dmytro", 26, "munich");
        firstContact = new Contact(0, ContactType.SKYPE, "ljubarskyj");
        secondContact = new Contact(0, ContactType.VIBER, "ljubarskyj");
        firstContactExpected = new Contact(0, 0, ContactType.SKYPE, "ljubarskyj");
        secondContactExpected = new Contact(1, 0, ContactType.VIBER, "ljubarskyj");
    }

    @Test
    public void shouldInsertContactToDatabase() {
        personDaoImpl.insert(firstPerson);
        contactDaoImpl.insert(firstContact);
        assertEquals(singletonList(firstContactExpected), contactDaoImpl.getByPersonId(0));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionByInsertionNull() {
        personDaoImpl.insert(firstPerson);
        contactDaoImpl.insert(null);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenGetNullPersonId() {
        contactDaoImpl.getByPersonId(null);
    }

//    @Test
//    public void shouldGetListOfContactsByPersonId() {
//        personDaoImpl.insert(firstPerson);
//        contactDaoImpl.insert(firstContact);
//        contactDaoImpl.insert(secondContact);
//        assertEquals(asList(firstContactExpected, secondContactExpected), contactDaoImpl.getByPersonId(0));
//    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenDoNotExistAppropriatePersonId() {
        contactDaoImpl.insert(firstContact);
    }


    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenPersonIdIsNull() {
        Contact contact = new Contact(null, ContactType.SKYPE, "ljubarskyj");
        contactDaoImpl.insert(contact);
    }
}

