package com.liubarska.db.person;

import com.liubarska.db.common.DaoRegistry;
import com.liubarska.db.delete.DeleteStrategy;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Iryna on 04.07.2018.
 */
public class PersonDaoImpl implements PersonDao {
    
    @Autowired
    private PersonFileManager personFileManager;
    @Autowired
    private DaoRegistry daoRegistry;
    private DeleteStrategy deleteStrategy;

    public PersonDaoImpl(DeleteStrategy deleteStrategy){
        this.deleteStrategy = deleteStrategy;
    }
    
    @PostConstruct
    public void init(){
        daoRegistry.put(Person.class, this);
    }

    @Override
    public void insert(@NonNull Person person) {
        if (person.getId() == null) {
            personFileManager.insert(person);
        } else {
            throw new IllegalArgumentException("Person id should be null");
        }
    }

    @Override
    public Person getById(@NonNull Integer id) {
        return personFileManager.getById(id);
    }

    @Override
    public List<Person> getBySurname(@NonNull String surname) {
        return personFileManager.getBySurname(surname);
    }

    @Override
    public void deleteById(@NonNull Integer id) {
        Person person = getById(id);
        if (person == null) {
            throw new RuntimeException("There is no person with such id");
        }
        deleteStrategy.delete(id);
        personFileManager.deleteById(id);
    }

    @Override
    public void deleteAll() {
        personFileManager.deleteAll();
    }
}
