package model.dao;

import model.Person;
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteAll() {
        Session session = getSession();
        Transaction tx = null;
        List<T> list = null;
        try {
            tx = session.beginTransaction();
            for (T entity : list = session.createCriteria(Person.class).list()) {
                session.delete(entity);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
