package com.liubarska.db.person;


import com.liubarska.db.person.Person;
import com.liubarska.db.person.PersonDao;
import com.liubarska.db.person.PersonDaoImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

/**
 * Created by Iryna on 04.07.2018.
 */
public class PersonDaoTest {

    private PersonDao personDao;
    private Person firstPerson;
    private Person secondPerson;
    private Person thirdPerson;
    private Person expectedFirstPerson;
    private Person expectedSecondPerson;
    private Person expectedThirdPerson;

    @Before
    public void before() throws IOException {
        prepareDao();
        prepareTestData();
    }

    private void prepareDao() {
        personDao = new PersonDaoImpl();
        personDao.deleteAll();
    }

    private void prepareTestData() {
        firstPerson = new Person("liubarskyi", "dmytro", 26, "munich");
        secondPerson = new Person("liubarska", "iryna", 29, "munich");
        thirdPerson = new Person("liubarska", "kateryna", 31, "kyiv");
        expectedFirstPerson = new Person(0, "liubarskyi", "dmytro", 26, "munich");
        expectedSecondPerson = new Person(1, "liubarska", "iryna", 29, "munich");
        expectedThirdPerson = new Person(2, "liubarska", "kateryna", 31, "kyiv");
    }

    @Test
    public void shouldInsertAndGetById() {
        personDao.insert(firstPerson);
        assertEquals(expectedFirstPerson, personDao.getById(firstPerson.getId()));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenInsertingNull() {
        personDao.insert(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenIdIsPresent() {
        personDao.insert(new Person(0, "liubarska", "kateryna", 31, "kyiv"));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenGetNullPersonId() {
        personDao.getById(null);
    }

    @Test
    public void shouldReturnNullIfThereIsNoSuchId() {
        assertNull(personDao.getById(0));
    }

    @Test
    public void shouldInsertTwoPeopleWithDifferentIds() {
        personDao.insert(firstPerson);
        personDao.insert(secondPerson);

        Person actualFirstPerson = personDao.getById(firstPerson.getId());
        Person actualSecondPerson = personDao.getById(secondPerson.getId());

        assertEquals(expectedFirstPerson, actualFirstPerson);
        assertEquals(expectedSecondPerson, actualSecondPerson);
    }

    @Test
    public void shouldGetListOfPeopleBySurname() {
        personDao.insert(firstPerson);
        personDao.insert(secondPerson);
        personDao.insert(thirdPerson);

        Collection<Person> personListBySurname = personDao.getBySurname(secondPerson.getSurname());

        assertEquals(asList(expectedSecondPerson, expectedThirdPerson), personListBySurname);
    }

    @Test
    public void shouldGetEmptyListOfPeopleWhenNoSuchSurname() {
        personDao.insert(firstPerson);
        List<Person> peopleList = personDao.getBySurname("tsukanova");

        List<Person> expectedEmptyList = new ArrayList<>();
        assertEquals(expectedEmptyList, peopleList);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenGettingByNullSurname() {
        personDao.getBySurname(null);
    }

    @Test
    public void shouldDeletePerson() {
        personDao.insert(firstPerson);
        personDao.deleteById(firstPerson.getId());
        assertNull(personDao.getById(firstPerson.getId()));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenNoSuchId() {
        personDao.deleteById(firstPerson.getId());
    }

    @Test
    public void shouldDeleteAllPeople() {
        personDao.insert(firstPerson);
        personDao.insert(secondPerson);
        personDao.insert(thirdPerson);

        personDao.deleteAll();

        assertNull(personDao.getById(firstPerson.getId()));
        assertNull(personDao.getById(secondPerson.getId()));
        assertNull(personDao.getById(thirdPerson.getId()));
    }
}
