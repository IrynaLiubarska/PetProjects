package model.dao;

import model.contact.Contact;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Iryna on 05.08.2018.
 */
public class ContactDaoImpl extends AbstractDao<Contact> implements ContactDao {

    public ContactDaoImpl() {
        super(Contact.class);
    }

    public List<Contact> getByPersonId(Integer personId) {
        Session session = getSession();
        Transaction tx = null;
        List<Contact> contacts = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Contact.class);
            Criterion contactWithThisPersonId = Restrictions.eq("person.id", personId);
            contacts = cr.add(contactWithThisPersonId).list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
        return contacts;
    }

    public void deleteByPersonId(Integer personId) {
        Session session = getSession();
        Transaction tx = null;
        List<Contact> contacts = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Contact.class);
            Criterion contactWithThisPersonId = Restrictions.eq("person.id", personId);
            for (Object contact : contacts = cr.add(contactWithThisPersonId).list()) {
                session.delete(contact);
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
