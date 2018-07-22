package com.liubarska.db.contact;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.liubarska.db.Constants.*;

/**
 * Created by Iryna on 05.07.2018.
 */
public class ContactFileManager {

    private final static String CONTACT_FILE = "C:\\Users\\Iryna\\Desktop\\contactData.txt";

    public String readById(Integer wantedId) {
        String record = null;
        try (BufferedReader br = new BufferedReader(new FileReader(CONTACT_FILE))) {
            String currentRecord;
            while ((currentRecord = br.readLine()) != null) {
                String[] fields = currentRecord.split(FIELD_SEPARATOR);
                String id = fields[0];
                if (id.equals(wantedId.toString())) {
                    record = currentRecord;
                    if (fields[2].equals(TOMBSTONE)) { // TODO test this case
                        return null;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(FAILED_TO_ACCESS_FILE);
        }
        return record;
    }

    public List<String> readByPersonId(String wantedPersonId) {
        Map<String, String> contactIdToPersonalRecord = new HashMap<>();
        List<String> records = new ArrayList<>();
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(CONTACT_FILE))) {
                String currentRecord;
                while ((currentRecord = br.readLine()) != null) {
                    String[] record = currentRecord.split(FIELD_SEPARATOR);
                    addToMapIfExist(wantedPersonId, contactIdToPersonalRecord, currentRecord, record);
                    removeFromMapIfDelete(contactIdToPersonalRecord, record);
                }
                records.addAll(contactIdToPersonalRecord.values());
            }
        } catch (IOException e) {
            throw new RuntimeException(FAILED_TO_ACCESS_FILE);
        }
        return records;
    }

    private void addToMapIfExist(String wantedPersonId, Map<String, String> contactIdToPersonalRecord, String currentRecord, String[] fields) {
        String personId = fields[1];
        if (personId.equals(wantedPersonId)) {
            String contactId = fields[0];
            contactIdToPersonalRecord.put(contactId, currentRecord);
        }
    }

    private void removeFromMapIfDelete(Map<String, String> contactIdToPersonalRecord, String[] fields) {
        if (fields[2].equals(TOMBSTONE)) {
            contactIdToPersonalRecord.remove(fields[0]);
        }
    }

    public void writeToFile(String record) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CONTACT_FILE, true))) {
            bw.write(record);
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(FAILED_TO_ACCESS_FILE);
        }
    }

    public void deleteFromFile(Integer contactId, ContactDeserializer contactDeserializer) {
        String record = readById(contactId);
        Contact contact = contactDeserializer.deserialize(record);
        writeToFile(Integer.toString(contactId) + FIELD_SEPARATOR + contact.getPersonId() + FIELD_SEPARATOR + TOMBSTONE);
    }

    public void makeEmpty() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CONTACT_FILE, false))) {
            bw.write("");
        } catch (IOException e) {
            throw new RuntimeException(FAILED_TO_ACCESS_FILE);
        }
    }

    public Integer readLargestId() {
        int max = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(CONTACT_FILE))) {
            String record;
            while ((record = br.readLine()) != null) {
                String[] fields = record.split(FIELD_SEPARATOR);
                Integer id = Integer.parseInt(fields[0]);
                if (id > max) {
                    max = id;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(FAILED_TO_ACCESS_FILE);
        }
        return max;
    }
}
