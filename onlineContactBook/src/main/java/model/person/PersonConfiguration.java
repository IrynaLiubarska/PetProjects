package model.person;

import model.dao.PersonDaoImpl;
import org.springframework.context.annotation.Bean;

/**
 * Created by Iryna on 14.08.2018.
 */
public class PersonConfiguration {
    @Bean
    public PersonDaoImpl personDao() {
        return new PersonDaoImpl();
    }
}
