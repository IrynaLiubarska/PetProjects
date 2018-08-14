package personTest;

import model.person.Person;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Iryna on 02.08.2018.
 */
public class PersonTest {

    private Person defaultPerson = new Person("Dmytro", "Liubarskyi", 26, "Munich");

    @Test
    public void shouldConstructPerson() {
        Person actualPerson = new Person("Dmytro", "Liubarskyi", 26, "Munich");
        assertEquals(defaultPerson, actualPerson);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenCreatingPersonWithNullName() {
        new Person(null, "Liubarskyi", 26, "Munich");
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenCreatingPersonWithNullLastName() {
        new Person("Dmytro", null, 26, "Munich");
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenCreatingPersonWithNullAge() {
        new Person("Dmytro", "Liubarskyi", null, "Munich");
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenCreatingPersonWithNullCity() {
        new Person("Dmytro", "Liubarskyi", 26, null);
    }
    
    @Test
    public void shouldReturnName() {
        assertEquals("Dmytro", defaultPerson.getName());
    }

    @Test
    public void shouldReturnLastName() {
        assertEquals("Liubarskyi", defaultPerson.getLastName());
    }
    
    @Test 
    public void shouldReturnAge(){
    assertEquals((Integer) 26, defaultPerson.getAge());
    }
    
    @Test
    public void shouldReturnCity(){
        assertEquals("Munich", defaultPerson.getCity());
    }

}
