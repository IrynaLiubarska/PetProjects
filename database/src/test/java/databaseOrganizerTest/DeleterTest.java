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

/**
 * Created by Iryna on 10.07.2018.
 */
public class DeleterTest {

    private Person firstPerson;
    private Person secondPerson;
    private Person thirdPerson;
    private Contact firstContact;
    private Contact secondContact;
    private PersonDao personDaoImpl;
    private ContactDao contactDaoImpl;

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
//        personDaoImpl.removeAll();
//        contactDaoImpl.removeAll();
//    }

    @Test
    public void shouldDeleteFirstPerson() {
        personDaoImpl = new PersonDaoImpl(DeletePolicy.DELETE_NO_ACTION);
        contactDaoImpl = new ContactDaoImpl(DeletePolicy.DELETE_NO_ACTION);

        personDaoImpl.insert(firstPerson);
        personDaoImpl.insert(secondPerson);
        personDaoImpl.insert(thirdPerson);
        contactDaoImpl.insert(firstContact);
        contactDaoImpl.insert(secondContact);

        personDaoImpl.delete(0);
    }

    @Test
    public void shouldNotDeleteFirstPersonBecauseOfExistingContactByRestrictedDeletePolicy() {
        PersonDao personDaoImpl = new PersonDaoImpl(DeletePolicy.DELETE_RESTRICT);
        ContactDao contactDaoImpl = new ContactDaoImpl(DeletePolicy.DELETE_RESTRICT);

        personDaoImpl.insert(firstPerson);
        personDaoImpl.insert(secondPerson);
        personDaoImpl.insert(thirdPerson);
        contactDaoImpl.insert(firstContact);
        contactDaoImpl.insert(secondContact);

        personDaoImpl.delete(0);
    }
    
    @Test
    public void shouldDeleteCascadePersonAndAllContacts(){
        PersonDao personDaoImpl = new PersonDaoImpl(DeletePolicy.DELETE_CASCADE);
        ContactDao contactDaoImpl = new ContactDaoImpl(DeletePolicy.DELETE_CASCADE);

        personDaoImpl.insert(firstPerson);
        personDaoImpl.insert(secondPerson);
        personDaoImpl.insert(thirdPerson);
        contactDaoImpl.insert(firstContact);
        contactDaoImpl.insert(secondContact);

        personDaoImpl.delete(0);
    }
}
