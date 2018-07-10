import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Iryna on 02.07.2018.
 */
public class PersonTest {
    private Person person;

    @Before
    public void setup() {
        person = new Person(1, "Liubarskyi", "Dmytro", 26, "Munich");
    }

    @Test
    public void shouldGetIndex() {
        assertEquals(1, person.getIndex());
    }
    
    @Test
    public void shouldGetSurname(){
        assertEquals("Liubarskyi", person.getSurname());
    }

    @Test
    public void shouldGetName(){
        assertEquals("Dmytro", person.getName());
    }
    
    @Test
    public void shouldGetAge(){
        assertEquals(26, person.getAge());
    }
    
    @Test
    public void shouldGetCity(){
        assertEquals("Munich", person.getCity());
    }
}
