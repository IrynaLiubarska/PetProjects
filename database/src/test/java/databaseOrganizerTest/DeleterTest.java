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

import javax.naming.NoPermissionException;

import static org.junit.Assert.fail;

/**
 * Created by Iryna on 10.07.2018.
 */
public class DeleterTest {

    private Person firstPerson;
    private Person secondPerson;
    private Person thirdPerson;
    private Contact firstContact;
    private Contact secondContact;
    private PersonDaoImpl personDaoImpl;
    private ContactDaoImpl contactDaoImpl;

    @Before
    public void createPeople() {
        firstPerson = new Person("liubarskyi", "dmytro", 26, "munich");
        secondPerson = new Person("liubarska", "iryna", 29, "munich");
        thirdPerson = new Person("liubarska", "kateryna", 31, "kyiv");

        firstContact = new Contact(0, ContactType.SKYPE, "ljubarskyj");
        secondContact = new Contact(0, ContactType.VIBER, "ljubarskyj");
    }

//    @After
//    public void removeAllRecords() throws IOException {
//        personDaoImpl.deleteAll();
//        contactDaoImpl.deleteAll();
//    }

    @Test(expected = RuntimeException.class)
    public void shouldDeleteFirstPerson() {
        contactDaoImpl = new ContactDaoImpl();
        personDaoImpl = new PersonDaoImpl(DeletePolicy.DELETE_NO_ACTION, contactDaoImpl);
        contactDaoImpl.setPersonDao(personDaoImpl);
        
        personDaoImpl.insert(firstPerson);
        personDaoImpl.insert(secondPerson);
        personDaoImpl.insert(thirdPerson);
        contactDaoImpl.insert(firstContact);
        contactDaoImpl.insert(secondContact);

        personDaoImpl.delete(0);
        personDaoImpl.getById(0);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotDeleteFirstPersonBecauseOfExistingContactByRestrictedDeletePolicy() throws NoPermissionException {
        ContactDao contactDaoImpl = new ContactDaoImpl();
        PersonDao personDaoImpl = new PersonDaoImpl(DeletePolicy.DELETE_RESTRICT, contactDaoImpl);

        personDaoImpl.insert(firstPerson);
        personDaoImpl.insert(secondPerson);
        personDaoImpl.insert(thirdPerson);
        contactDaoImpl.insert(firstContact);
        contactDaoImpl.insert(secondContact);

        personDaoImpl.delete(firstPerson.getId());
    }

    @Test(expected = RuntimeException.class)
    public void shouldDeleteCascadePersonAndAllContacts() throws NoPermissionException {
        ContactDao contactDaoImpl = new ContactDaoImpl();
        PersonDao personDaoImpl = new PersonDaoImpl(DeletePolicy.DELETE_CASCADE, contactDaoImpl);

        personDaoImpl.insert(firstPerson);
        personDaoImpl.insert(secondPerson);
        personDaoImpl.insert(thirdPerson);
        contactDaoImpl.insert(firstContact);
        contactDaoImpl.insert(secondContact);

        personDaoImpl.delete(firstPerson.getId());
        try {
            personDaoImpl.getById(firstPerson.getId());
            fail();
        } catch (RuntimeException e) {
        }
        try {
            contactDaoImpl.getByPersonId(firstPerson.getId());
            fail();
        } catch (RuntimeException e) {
        }
    }
}
