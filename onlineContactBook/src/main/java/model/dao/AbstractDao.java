package model.dao;

import model.person.Person;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


/**
 * Created by Iryna on 05.08.2018.
 */
public class AbstractDao<T> {
    
    private Class<T> tClass;

    protected AbstractDao(Class<T> tClass) {
        this.tClass = tClass;
    }
    
    protected Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }
    
    public void insert(T entity) {
        if(entity==null){
            throw new NullPointerException("there is no object");
        }
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public T getById(Integer id) {
        Session session = getSession();
        Transaction tx = null;
        T entity = null;
        try {
            tx = session.beginTransaction();
            entity = session.get(tClass, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
           throw e;
        } finally {
            session.close();
        }
        return entity;
    }

    public void deleteById(Integer id) {
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(getById(id));
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void deleteAll() {
        Session session = getSession();
        Transaction tx = null;
        List<T> entities = null;
        try {
            tx = session.beginTransaction();
            for (T entity : entities = session.createCriteria(Person.class).list()) {
                session.delete(entity);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
           throw e;
        } finally {
            session.close();
        }
    }

}
