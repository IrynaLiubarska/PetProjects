package com.liubarska.db.configuration;

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
 * Created by Iryna on 30.07.2018.
 */
public class PersonConfiguration {
    @Bean
    @Qualifier("deleteRestrict")
    public PersonDaoImpl personDaoRestrict(DeleteRestrict deleteRestrict) {
        return new PersonDaoImpl(deleteRestrict);
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
    public PersonFileManager personFileManager() {
        return new PersonFileManager();
    }

    @Bean
    public PersonSerializer personSerializer() {
        return new PersonSerializer();
    }

    @Bean
    public PersonDeserializer personDeserializer() {
        return new PersonDeserializer();
    }
}
