package com.liubarska.db.contact;

import com.liubarska.db.common.Deserializer;

import static com.liubarska.db.common.Constants.FIELD_SEPARATOR;

/**
 * Created by Iryna on 05.07.2018.
 */
public class ContactDeserializer implements Deserializer<Contact> {

    @Override
    public Contact deserialize(String record) {
        String[] fields = record.split(FIELD_SEPARATOR);
        Integer id = Integer.parseInt(fields[0]);
        Integer personId = Integer.parseInt(fields[1]);
        ContactType contactType = ContactType.valueOf(fields[2]);
        String value = fields[3];
        return new Contact(id, personId, contactType, value);
    }
}
