package com.liubarska.db.person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.liubarska.db.Constants.*;


/**
 * Created by Iryna on 03.07.2018.
 */
public class PersonFileManager {

    private final static String PERSON_FILE = "C:\\Users\\Iryna\\Desktop\\personalData.txt";

    public String readById(Integer personId) {
        String record = null;
        try (BufferedReader br = new BufferedReader(new FileReader(PERSON_FILE))) {
            String currentRecord;
            while ((currentRecord = br.readLine()) != null) {
                String[] fields = currentRecord.split(FIELD_SEPARATOR);
                String id = fields[0];
                if (id.equals(Integer.toString(personId))) {
                    record = currentRecord;
                    if (fields[1].equals(TOMBSTONE)) {
                        return null;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(FAILED_TO_ACCESS_FILE);
        }
        return record;
    }

    public List<String> readBySurname(String wantedSurname) {
        List<String> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PERSON_FILE))) {
            String currentRecord;
            while ((currentRecord = br.readLine()) != null) {
                String[] fields = currentRecord.split(FIELD_SEPARATOR);
                String surname = fields[1];
                if (surname.equals(wantedSurname)) {
                    records.add(currentRecord);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(FAILED_TO_ACCESS_FILE);
        }
        return records;
    }

    public void writeToFile(String record) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PERSON_FILE, true))) {
            bw.write(record);
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(FAILED_TO_ACCESS_FILE);
        }
    }

    public void delete(Integer id) {
        writeToFile(Integer.toString(id) + ", TOMBSTONE");
    }

    public void makeEmpty() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PERSON_FILE, false))) {
            bw.write("");
        } catch (IOException e) {
            throw new RuntimeException(FAILED_TO_ACCESS_FILE);
        }
    }

    public Integer readLargestId() {
        int max = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(PERSON_FILE))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] line = currentLine.split(FIELD_SEPARATOR);
                Integer id = Integer.parseInt(line[0]);
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
