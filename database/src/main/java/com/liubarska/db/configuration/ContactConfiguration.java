package com.liubarska.db.configuration;

import com.liubarska.db.contact.ContactDaoImpl;
import com.liubarska.db.contact.ContactDeserializer;
import com.liubarska.db.contact.ContactFileManager;
import com.liubarska.db.contact.ContactSerializer;
import org.springframework.context.annotation.Bean;

/**
 * Created by Iryna on 30.07.2018.
 */
public class ContactConfiguration {
    @Bean
    public ContactDaoImpl contactDao() {
        return new ContactDaoImpl();
    }

    @Bean
    public ContactFileManager contactFileManager() {
        return new ContactFileManager();
    }

    @Bean
    public ContactSerializer contactSerializer() {
        return new ContactSerializer();
    }

    @Bean
    public ContactDeserializer contactDeserializer() {
        return new ContactDeserializer();
    }

}
