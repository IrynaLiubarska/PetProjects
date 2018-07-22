package com.liubarska.db.contact;

import com.liubarska.db.common.FileManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.liubarska.db.common.Constants.*;

/**
 * Created by Iryna on 05.07.2018.
 */
public class ContactFileManager extends FileManager {

    private final static String CONTACT_FILE = "contactData.txt";
    private ContactSerializer contactSerializer = new ContactSerializer();
    private ContactDeserializer contactDeserializer = new ContactDeserializer();

    public ContactFileManager() {
        super(CONTACT_FILE);
    }

    public void insert(Contact contact) {
        String record = createRecord(contact);
        writeToFile(record);
    }

    private String createRecord(Contact contact) {
        contact.setId(currentId++);
        return contactSerializer.serialize(contact);
    }

    public Contact getById(int id) {
        String record = doGetById(id);
        if (record == null) {
            return null;
        }
        return contactDeserializer.deserialize(record);
    }

    public List<Contact> getByPersonId(int personId) {
        Map<String, String> contactIdToPersonalRecord = new HashMap<>();
        List<String> records = new ArrayList<>();
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String currentRecord;
                while ((currentRecord = br.readLine()) != null) {
                    String[] record = currentRecord.split(FIELD_SEPARATOR);
                    addToMapIfExist(personId, contactIdToPersonalRecord, currentRecord, record);
                    removeFromMapIfDelete(contactIdToPersonalRecord, record);
                }
                records.addAll(contactIdToPersonalRecord.values());
            }
        } catch (IOException e) {
            throw new RuntimeException(FAILED_TO_ACCESS_FILE);
        }
        return getContacts(records);
    }

    private List<Contact> getContacts(List<String> records) {
        List<Contact> contacts = new ArrayList<>();
        if (records.isEmpty()) {
            return contacts;
        }
        for (String record : records) {
            Contact contact = contactDeserializer.deserialize(record);
            contacts.add(contact);
        }
        return contacts;
    }

    private void addToMapIfExist(int personId, Map<String, String> contactIdToPersonalRecord, String currentRecord, String[] fields) {
        if (fields[1].equals(String.valueOf(personId))) {
            String contactId = fields[0];
            contactIdToPersonalRecord.put(contactId, currentRecord);
        }
    }

    private void removeFromMapIfDelete(Map<String, String> contactIdToPersonalRecord, String[] fields) {
        if (fields[1].equals(TOMBSTONE)) {
            contactIdToPersonalRecord.remove(fields[0]);
        }
    }
}
