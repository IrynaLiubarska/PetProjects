package model.dao;

import model.Person;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Iryna on 05.08.2018.
 */
public class PersonDaoImplement extends AbstractDao<Person> implements PersonDao {

    public PersonDaoImplement(){super(Person.class);}

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
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
    
}
