package databaseOrganizer.contact;

/**
 * Created by Iryna on 03.07.2018.
 */
public class ContactSerializer {

    public String serialize(Contact contact) {
        return contact.getId() + ", " + contact.getPersonId() + ", " + contact.getContactType() + ", " + contact.getValue();
    }
}
