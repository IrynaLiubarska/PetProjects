import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Iryna on 02.07.2018.
 */
public class PersonSerializerTest {
    private PersonSerializer personSerializer = new PersonSerializer();
    
    @Test
    public void shouldSerialize(){
        Person person = new Person(1, "Liubarskyi", "Dmytro", 26, "Munich");
        assertEquals("1, Liubarskyi, Dmytro, 26, Munich", personSerializer.serialize(person));
    }
}
