package com.liubarska.dbTest;

import com.liubarska.db.person.Person;
import org.junit.Test;

/**
 * Created by Iryna on 10.07.2018.
 */
public class PersonTest {
    
    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenCreatingPersonWithNullSurname() {
        new Person(null, "dmytro", 26, "munich");
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenCreatingPersonWithNullName(){
        new Person("liubarskyi", null, 26, "munich");
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenCreatingPersonWithNullAge() {
        new Person("liubarskyi", "dmytro", null, "munich");
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenCreatingPersonWithNullCity() {
        new Person("liubarskyi", "dmytro", 26, null);
    }
    
}

