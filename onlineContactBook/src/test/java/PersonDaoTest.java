import model.Person;
import model.PersonConfiguration;
import model.dao.PersonDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Iryna on 14.08.2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersonConfiguration.class})
public class PersonDaoTest {
    @Autowired
    PersonDao personDao;
    private Person firstPerson;
    private Person secondPerson;
    private Person thirdPerson;

    @Before
    public void before(){
        firstPerson = new Person("Dmytro", "Liubarskyi", 26, "Munich");
        secondPerson = new Person("Iryna", "Liubarska", 29, "Munich");
        thirdPerson = new Person("Kateryna", "Liubarska", 31, "kyiv");
    }
    
    @Test
    public void shouldInsertPersonInDatabase() {
        personDao.insert(firstPerson);
        assertEquals(firstPerson, personDao.getById(firstPerson.getId()));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenInsertingNull() {
        personDao.insert(null);
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenInsertingPersonWithOneNullName() {
        personDao.insert(new Person(null, "Liubarskyi", 26, "Munich"));
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenInsertingPersonWithOneNullLastName() {
        personDao.insert(new Person("Dmytro", null, 26, "Munich"));
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenInsertingPersonWithOneNullAge() {
        personDao.insert(new Person("Dmytro", "Liubarskyi", null, "Munich"));
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenInsertingPersonWithOneNullCity() {
        personDao.insert(new Person("Dmytro", "Liubarskyi", 26, null));
    }
    
    @Test
    public void shouldGetPersonBySurname() {
        personDao.insert(secondPerson);
        assertEquals(asList(secondPerson), personDao.getBySurname(secondPerson.getLastName()));
    }
    
    @Test
    public void shouldDeleteAll(){
        personDao.deleteAll();
        assertNull(personDao.getById(firstPerson.getId()));
    }
    
    @Test
    public void shouldDeletePersonById(){
        personDao.insert(thirdPerson);
        personDao.getById(thirdPerson.getId());
        assertNull(personDao.getById(thirdPerson.getId()));
    }

}
