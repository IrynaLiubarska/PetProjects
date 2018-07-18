package databaseOrganizer.contact;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Iryna on 05.07.2018.
 */
public class ContactFileManager {

    private final static String CONTACT_FILE = "C:\\Users\\Iryna\\Desktop\\contactData.txt";

    public String readById(Integer wantedId) {
        String record = null;
        try (BufferedReader br = new BufferedReader(new FileReader(CONTACT_FILE))) {
            try {
                String currentRecord;
                while ((currentRecord = br.readLine()) != null) {
                    String[] fields = currentRecord.split(",");
                    String id = fields[0];
                    if (id.equals(wantedId.toString())) {
                        record = currentRecord;
                        checkIfDelete(fields);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(wantedId + " Failed reading by id");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read file");
        }
        return record;
    }

    private void checkIfDelete(String[] fields) {
        if (fields[2].equals("DELETE")) {
            throw new RuntimeException("The contact with this id was deleted");
        }
    }

    public List<String> readByPersonId(String wantedPersonId) {
        Map<String, String> contactIdToPersonalRecord = new HashMap<>();
        List<String> records = new ArrayList<>();
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(CONTACT_FILE))) {
                String currentRecord;
                try {
                    while ((currentRecord = br.readLine()) != null) {
                        String[] record = currentRecord.split(", ");
                        addToMapIfExist(wantedPersonId, contactIdToPersonalRecord, currentRecord, record);
                        removeFromMapIfDelete(contactIdToPersonalRecord, record);
                    }
                    records.addAll(contactIdToPersonalRecord.values());
                } catch (IOException e) {
                    throw new RuntimeException("Failed reading by person id");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
        if (fields[2].equals("DELETE")) {
            contactIdToPersonalRecord.remove(fields[0]);
        }
    }

    public void writeToFile(String record) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CONTACT_FILE, true))) {
            bw.write(record);
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Failed to write file");
        }
    }

    public void makeEmpty() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CONTACT_FILE, false))) {
            bw.write("");
        } catch (IOException e) {
            throw new RuntimeException("Failed to remove all elements");
        }
    }

    public Integer readLargestId() {
        int max = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(CONTACT_FILE))) {
            String record;
            while ((record = br.readLine()) != null) {
                String[] fields = record.split(", ");
                Integer id = Integer.parseInt(fields[0]);
                if (id > max) {
                    max = id;
                }
            }
        } catch (IOException e) {
            // this is expected if database is empty (file does not exists)
        }
        return max;
    }
}
