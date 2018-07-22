package com.liubarska.db.person;

import static com.liubarska.db.Constants.FIELD_SEPARATOR;

/**
 * Created by Iryna on 03.07.2018.
 */
public class PersonSerializer {

    public String serialize(Person person) {
        return person.getId() + FIELD_SEPARATOR + person.getSurname() + FIELD_SEPARATOR + person.getName() + FIELD_SEPARATOR + person.getAge() + FIELD_SEPARATOR + person.getCity();
    }
}
