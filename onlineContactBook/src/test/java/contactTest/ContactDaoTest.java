package contactTest;

import model.contact.Contact;
import model.contact.ContactConfiguration;
import model.contact.ContactType;
import model.dao.ContactDao;
import model.dao.PersonDao;
import model.person.Person;
import model.person.PersonConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        personDao.insert(firstPerson);
        contactDao.insert(firstContact);
        assertEquals(firstContact, contactDao.getById(firstContact.getId()));
    }
    
    @Test
    public void shouldDeleteAllContactFromDatabase(){
        personDao.insert(firstPerson);
        contactDao.insert(firstContact);
        contactDao.deleteAll();
        assertNull(contactDao.getById(firstContact.getId()));
    }
}
