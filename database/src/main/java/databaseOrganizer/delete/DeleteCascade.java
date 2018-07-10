package databaseOrganizer.delete;

import databaseOrganizer.contact.Contact;
import databaseOrganizer.contact.ContactDeserializer;
import databaseOrganizer.contact.ContactFileManager;
import databaseOrganizer.person.PersonFileManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iryna on 09.07.2018.
 */
public class DeleteCascade implements Deleter {
    private PersonFileManager personFileManager = new PersonFileManager();
    private ContactFileManager contactFileManager = new ContactFileManager();
    private ContactDeserializer contactDeserializer = new ContactDeserializer();

    @Override
    public void delete(Integer personId) {
        List<Contact> contactList = new ArrayList<>();
        List<String> transactionLine = contactFileManager.readByPersonId(Integer.toString(personId));
        if (transactionLine.isEmpty()) {
            throw new RuntimeException();
        }
        for (String str : transactionLine) {
            Contact contact = contactDeserializer.deserialize(str);
            contactList.add(contact);
        }
        if (contactList.isEmpty()) {
            try {
                personFileManager.writeToFile("person and contact with this person id is deleted " + Integer.toString(personId));
            } catch (IOException e) {
                throw new RuntimeException("Failed to delete person");
            }
        }else{
            try {
                personFileManager.writeToFile("person with this person id is deleted " + Integer.toString(personId));
                contactFileManager.writeToFile("person and contact with this person id is deleted " + Integer.toString(personId));
            } catch (IOException e) {
                throw new RuntimeException("Failed to delete person and contact");
            }
        }
    }
}
