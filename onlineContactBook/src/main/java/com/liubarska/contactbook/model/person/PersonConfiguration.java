package com.liubarska.contactbook.model.person;

import com.liubarska.contactbook.model.dao.PersonDaoImpl;
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
