package contactTest;

import model.contact.Contact;
import model.contact.ContactType;
import model.person.Person;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Iryna on 02.08.2018.
 */
public class ContactTest {
   
    private Person defaultPerson = new Person("Dmytro", "Liubarskyi", 26, "Munich");
    private Contact defaultContact = new Contact(defaultPerson, ContactType.SKYPE, "Liubarskyi");

    @Test
    public void shouldCreateContact() {
        Contact contact = new Contact(defaultPerson, ContactType.SKYPE, "Liubarskyi");
        assertEquals(defaultContact, contact);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenCreatingContactWithNullPerson() {
        new Contact(null, ContactType.SKYPE, "Liubarskyi");
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenCreatingContactWithNulContactType() {
        new Contact(defaultPerson, null, "Liubarskyi");
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenCreatingContactWithNullValue() {
        new Contact(defaultPerson, ContactType.SKYPE, null);
    }
    
}
