package com.liubarska.db.contact;

import com.liubarska.db.common.Serializer;

import static com.liubarska.db.common.Constants.FIELD_SEPARATOR;

/**
 * Created by Iryna on 03.07.2018.
 */
public class ContactSerializer implements Serializer<Contact> {
   
    @Override
    public String serialize(Contact contact) {
        return contact.getId() + FIELD_SEPARATOR + contact.getPersonId() + FIELD_SEPARATOR + contact.getContactType() + FIELD_SEPARATOR + contact.getValue();
    }
}
