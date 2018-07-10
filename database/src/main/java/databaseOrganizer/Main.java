package databaseOrganizer;

import databaseOrganizer.contact.Contact;
import databaseOrganizer.contact.ContactDaoImpl;
import databaseOrganizer.delete.DeletePolicy;
import databaseOrganizer.person.Person;
import databaseOrganizer.person.PersonDaoImpl;

import java.io.IOException;

/**
 * Created by Iryna on 03.07.2018.
 */
public class Main {

    private static ConsoleReader consoleReader = new ConsoleReader();
    private static PersonDaoImpl personDao = new PersonDaoImpl(DeletePolicy.DELETE_NO_ACTION);
    private static ContactDaoImpl contactDao = new ContactDaoImpl(DeletePolicy.DELETE_NO_ACTION);

    public static void main(String[] args) throws IOException {
        Person person = consoleReader.readPerson();
        personDao.insert(person);
        Contact contact = consoleReader.readContact();
        contactDao.insert(contact);
    }
    
}
