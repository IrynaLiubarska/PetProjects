package com.liubarska.db.person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iryna on 04.07.2018.
 */
public class PersonDeserializer {

    public Person deserialize(String record) {
        String[] fields = record.split(", ");
        int id = Integer.parseInt(fields[0]);
        String surname = fields[1];
        String name = fields[2];
        int age = Integer.parseInt(fields[3]);
        String city = fields[4];
        return new Person(id, surname, name, age, city);
    }

    public List<Person> deserialize(List<String> records) {
        List<Person> persons = new ArrayList<>();
        for (String record : records) {
            Person person = deserialize(record);
            persons.add(person);
        }
        return persons;
    }
}
