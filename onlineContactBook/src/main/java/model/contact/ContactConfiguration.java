package model.contact;

import model.dao.ContactDao;
import model.dao.ContactDaoImpl;
import org.springframework.context.annotation.Bean;

/**
 * Created by Iryna on 14.08.2018.
 */
public class ContactConfiguration {
    @Bean
    public ContactDao contactDao(){
        return new ContactDaoImpl();
    }
}
