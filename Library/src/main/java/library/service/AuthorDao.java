package library.service;


import library.model.Author;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AuthorDao {

    public void addAuthor(Author author) {
        SessionFactory sessionFactory = SessionFactoryHolder.getInstance();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(author);
        session.getTransaction().commit();
        session.close();
    }

    public Author getAuthorById(int id) {
        SessionFactory sessionFactory = SessionFactoryHolder.getInstance();
        Session session = sessionFactory.openSession();
        Author author = (Author) session.get(Author.class, id);
        session.close();
        return author;
    }

    public Set<Author> getAllAuthors() {
        HashSet<Author> authors = new HashSet<>();
        SessionFactory sessionFactory = SessionFactoryHolder.getInstance();
        Session session = sessionFactory.openSession();
        authors.addAll(session.createQuery("from Author").list());
        session.close();
        return authors;
    }

    public Set<Author> getAuthorByName(String lastName) {
        HashSet<Author> authors = new HashSet<>();
        SessionFactory sessionFactory = SessionFactoryHolder.getInstance();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Author where lastName = :lastName ");
        query.setParameter("lastName", lastName);
        List list = query.list();
        authors.addAll(list);
        return authors;
    }

}
