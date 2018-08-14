package model;

import model.dao.PersonDaoImplement;
import org.springframework.context.annotation.Bean;

/**
 * Created by Iryna on 14.08.2018.
 */
public class PersonConfiguration {
    @Bean
    public PersonDaoImplement personDao() {
        return new PersonDaoImplement();
    }
}
