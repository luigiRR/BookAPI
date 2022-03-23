package com.project.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.spring.dao.BookDAO;
import com.project.spring.model.Book;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookDAO bookDAO;
	
	//save the record
	@Override
	@Transactional
	public long save(Book book) {
		return bookDAO.save(book);
	}

	//get a single record
	@Override
	@Transactional
	public Book get(long id) {
		return bookDAO.get(id);
	}

	//get all the record
	@Override
	@Transactional
	public List<Book> list() {
		return bookDAO.list();
	}

	//update the record
	@Override
	@Transactional
	public void update(long id, Book book) {
		bookDAO.update(id, book);
		
	}

	//delete a record
	@Override
	@Transactional
	public void delete(long id) {
		bookDAO.delete(id);
	}

}
