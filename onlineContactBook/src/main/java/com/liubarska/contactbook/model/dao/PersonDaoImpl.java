package com.liubarska.contactbook.model.dao;

import com.liubarska.contactbook.model.person.Person;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Iryna on 05.08.2018.
 */
public class PersonDaoImpl extends AbstractDao<Person> implements PersonDao {

    public PersonDaoImpl() {
        super(Person.class);
    }

    public List<Person> getBySurname(String surname) {
        Session session = getSession();
        Transaction tx = null;
        List<Person> list = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Person.class);
            cr.add(Restrictions.ilike("lastName", surname + "%"));
            list = cr.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
        return list;
    }

}
