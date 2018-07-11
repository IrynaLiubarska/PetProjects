package databaseOrganizerTest;


import databaseOrganizer.contact.ContactDaoImpl;
import databaseOrganizer.delete.DeletePolicy;
import databaseOrganizer.person.Person;
import databaseOrganizer.person.PersonDao;
import databaseOrganizer.person.PersonDaoImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Iryna on 04.07.2018.
 */
public class PersonDaoTest {

    private PersonDao personDaoImpl = new PersonDaoImpl(DeletePolicy.DELETE_NO_ACTION, new ContactDaoImpl());
    private Person firstPerson;
    private Person secondPerson;
    private Person thirdPerson;
    private Person expectedFirstPerson;
    private Person expectedSecondPerson;
    private Person expectedThirdPerson;

    @Before
    public void removeAllRecords() throws IOException {
        personDaoImpl.deleteAll();
        firstPerson = new Person("liubarskyi", "dmytro", 26, "munich");
        secondPerson = new Person("liubarska", "iryna", 29, "munich");
        thirdPerson = new Person("liubarska", "kateryna", 31, "kyiv");
        expectedFirstPerson = new Person(0, "liubarskyi", "dmytro", 26, "munich");
        expectedSecondPerson = new Person(1, "liubarska", "iryna", 29, "munich");
        expectedThirdPerson = new Person(2, "liubarska", "kateryna", 31, "kyiv");
    }

    @Test
    public void shouldInsertPersonToDatabase() throws IOException {
        personDaoImpl.insert(firstPerson);

        List<Person> actualListOfPeople = personDaoImpl.getBySurname(firstPerson.getSurname());

        List<Person> expectedListOfPeople = new ArrayList<>();
        expectedListOfPeople.add(expectedFirstPerson);
        assertEquals(expectedListOfPeople, actualListOfPeople);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionByInsertionNull() {
        personDaoImpl.insert(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionByInsertPersonWithIdWhichWasPresentedBeforeInsertion(){
        personDaoImpl.insert(new Person(0, "liubarska", "kateryna", 31, "kyiv"));
    }

    @Test
    public void shouldInsertTwoPeopleWithDifferentIds() throws IOException {
        personDaoImpl.insert(this.firstPerson);
        personDaoImpl.insert(this.secondPerson);

        Person firstPerson = personDaoImpl.getById(this.firstPerson.getId());
        Person secondPerson = personDaoImpl.getById(this.secondPerson.getId());

        assertEquals(expectedFirstPerson, firstPerson);
        assertEquals(expectedSecondPerson, secondPerson);
    }

    @Test
    public void shouldGetPersonById() throws IOException {
        personDaoImpl.insert(firstPerson);
        assertEquals(expectedFirstPerson, personDaoImpl.getById(firstPerson.getId()));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenGetNullPersonId() {
        personDaoImpl.getById(null);
    }
   
    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionIfThereIsNoSuchId() throws IOException {
        personDaoImpl.getById(0);
    }

    @Test
    public void shouldGetListOfPeopleBySurname() throws IOException {
        personDaoImpl.insert(firstPerson);
        personDaoImpl.insert(secondPerson);
        personDaoImpl.insert(thirdPerson);

        List<Person> personListBySurname = personDaoImpl.getBySurname(secondPerson.getSurname());

        List<Person> expectedPersonListBySurname = new ArrayList<>();
        expectedPersonListBySurname.add(expectedSecondPerson);
        expectedPersonListBySurname.add(expectedThirdPerson);
        assertEquals(expectedPersonListBySurname, personListBySurname);
    }

    @Test
    public void shouldGetEmptyListOfPeopleWhenNoSuchSurname() throws IOException {
        personDaoImpl.insert(firstPerson);
        List<Person> peopleList = personDaoImpl.getBySurname("tsukanova");

        List<Person> expectedEmptyList = new ArrayList<>();
        assertEquals(expectedEmptyList, peopleList);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenGettingByNullSurname() {
        personDaoImpl.getBySurname(null);
    }
    
}
