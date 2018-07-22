package com.liubarska.db.person;

import com.liubarska.db.common.Serializer;

import static com.liubarska.db.common.Constants.FIELD_SEPARATOR;

/**
 * Created by Iryna on 03.07.2018.
 */
public class PersonSerializer implements Serializer<Person>{

    @Override
    public String serialize(Person person) {
        return person.getId() + FIELD_SEPARATOR + person.getSurname() + FIELD_SEPARATOR + person.getName() + FIELD_SEPARATOR + person.getAge() + FIELD_SEPARATOR + person.getCity();
    }
}
