package com.liubarska.db.person;

import com.liubarska.db.FileManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.liubarska.db.Constants.*;


/**
 * Created by Iryna on 03.07.2018.
 */
public class PersonFileManager extends FileManager {

    private final static String PERSON_FILE = "C:\\Users\\Iryna\\Desktop\\personalData.txt";
    private PersonSerializer personSerializer = new PersonSerializer();
    private PersonDeserializer personDeserializer = new PersonDeserializer();


    public PersonFileManager() {
        super(PERSON_FILE);
    }

    public void insert(Person person) {
        String record = createRecord(person);
        writeToFile(record);
    }

    public Person getById(int id) {
        String record = doGetById(id);
        if (record == null) {
            return null;
        }
        return personDeserializer.deserialize(record);
    }

    public List<Person> getBySurname(String wantedSurname) {
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
        return personDeserializer.deserialize(records);
    }

    private String createRecord(Person person) {
        person.setId(currentId++);
        return personSerializer.serialize(person);
    }
}
