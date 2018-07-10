package library.service;

import library.model.Book;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class BookDao {

    public void addBook(Book book) {
        SessionFactory sessionFactory = SessionFactoryHolder.getInstance();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        session.close();
    }

    public Book getBookById(int id) {
        SessionFactory sessionFactory = SessionFactoryHolder.getInstance();
        Session session = sessionFactory.openSession();
        Book book = (Book) session.get(Book.class, id);
        session.close();
        return book;
    }

    public Set<Book> getAllBooks() {
        HashSet<Book> books = new HashSet<>();
        SessionFactory sessionFactory = SessionFactoryHolder.getInstance();
        Session session = sessionFactory.openSession();
        books.addAll(session.createQuery("from Book").list());
        session.close();
        return books;
    }

    public Set<Book> getBookByPartOfName(String searchWord) {
        HashSet<Book> books = new HashSet<>();
        SessionFactory sessionFactory = SessionFactoryHolder.getInstance();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Book where name LIKE :searchWord ");
        query.setParameter("searchWord", searchWord + "%");
        List list = query.list();
        books.addAll(list);
        return books;
    }

    public Set<Book> getSearchResultByManyValues(String name, int yearOfPrint, String isbn) {
        HashSet<Book> books = new HashSet<>();
        SessionFactory sessionFactory = SessionFactoryHolder.getInstance();
        Session session = sessionFactory.openSession();
        Criteria cr = session.createCriteria(Book.class);

        if (name != null && !name.isEmpty())
            cr.add(Restrictions.like("name", "%" + name + "%"));
        if (yearOfPrint != 0)
            cr.add(Restrictions.eq("yearOfPrint", yearOfPrint));
        if (isbn != null && !isbn.isEmpty())
            cr.add(Restrictions.ilike("isbn", isbn + "%"));

        books.addAll((List<Book>) cr.list());
        return books;
    }


}
