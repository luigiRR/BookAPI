package com.project.spring.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.spring.model.Book;

@Repository
public class BookDAOImpl implements BookDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	//save the record
	@Override
	public long save(Book book) {
		sessionFactory.getCurrentSession().save(book);
		return book.getId();
	}

	//get a single record
	@Override
	public Book get(long id) {
		return sessionFactory.getCurrentSession().get(Book.class, id);
	}

	//get all the record
	@Override
	public List<Book> list() {
		List<Book> list = sessionFactory.getCurrentSession().createQuery("from Book").list();
		return list;
	}

	//update the record
	@Override
	public void update(long id, Book book) {
		Session session = sessionFactory.getCurrentSession();
		Book oldbook = session.byId(Book.class).load(id);
		oldbook.setTitle(book.getTitle());
		oldbook.setAuthor(book.getAuthor());
		session.flush();
	}

	//delet the record
	@Override
	public void delete(long id) {
		Session session = sessionFactory.getCurrentSession();
		Book book = session.byId(Book.class).load(id);
		session.delete(book);
	}

}
