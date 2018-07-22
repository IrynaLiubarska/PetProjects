package com.liubarska.db.contact;

import static com.liubarska.db.Constants.FIELD_SEPARATOR;

/**
 * Created by Iryna on 03.07.2018.
 */
public class ContactSerializer {

    public String serialize(Contact contact) {
        return contact.getId() + FIELD_SEPARATOR + contact.getPersonId() + FIELD_SEPARATOR + contact.getContactType() + FIELD_SEPARATOR + contact.getValue();
    }
}
