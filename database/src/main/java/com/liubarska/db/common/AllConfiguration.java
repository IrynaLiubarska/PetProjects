package com.liubarska.db.common;

import com.liubarska.db.contact.ContactDaoImpl;
import com.liubarska.db.contact.ContactDeserializer;
import com.liubarska.db.contact.ContactFileManager;
import com.liubarska.db.contact.ContactSerializer;
import com.liubarska.db.delete.DeleteCascade;
import com.liubarska.db.delete.DeleteNoAction;
import com.liubarska.db.delete.DeleteRestrict;
import com.liubarska.db.person.PersonDaoImpl;
import com.liubarska.db.person.PersonDeserializer;
import com.liubarska.db.person.PersonFileManager;
import com.liubarska.db.person.PersonSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

/**
 * Created by Iryna on 26.07.2018.
 */
public class AllConfiguration {
    
    @Bean
    public DaoRegistry daoRegistry() {
        return new DaoRegistry();
    }

    @Bean
    public ContactDaoImpl contactDao() {
        return new ContactDaoImpl();
    }

    @Bean
    @Qualifier("deleteNoAction")
    public PersonDaoImpl personDao(DeleteNoAction deleteNoAction) {
        return new PersonDaoImpl(deleteNoAction);
    }

    @Bean
    @Qualifier("deleteCascade")
    public PersonDaoImpl personDaoCascade(DeleteCascade deleteCascade) {
        return new PersonDaoImpl(deleteCascade);
    }

    @Bean
    @Qualifier("deleteRestrict")
    public PersonDaoImpl personDaoRestrict(DeleteRestrict deleteRestrict) {
        return new PersonDaoImpl(deleteRestrict);
    }

    @Bean
    public PersonFileManager personFileManager() {
        return new PersonFileManager();
    }

    @Bean
    public ContactFileManager contactFileManager() {
        return new ContactFileManager();
    }

    @Bean
    public PersonSerializer personSerializer() {
        return new PersonSerializer();
    }

    @Bean
    public PersonDeserializer personDeserializer() {
        return new PersonDeserializer();
    }

    @Bean
    public ContactSerializer contactSerializer() {
        return new ContactSerializer();
    }

    @Bean
    public ContactDeserializer contactDeserializer() {
        return new ContactDeserializer();
    }

    @Bean
    public DeleteNoAction deleteNoAction() {
        return new DeleteNoAction();
    }

    @Bean
    public DeleteCascade deleteCascade() {
        return new DeleteCascade();
    }

    @Bean
    public DeleteRestrict deleteRestrict() {
        return new DeleteRestrict();
    }
}
