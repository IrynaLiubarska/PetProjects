package com.liubarska.db.person;

import com.liubarska.db.common.Dao;

import java.util.List;

/**
 * Created by Iryna on 03.07.2018.
 */
public interface PersonDao extends Dao<Person> {

    List<Person> getBySurname(String surname);
}
