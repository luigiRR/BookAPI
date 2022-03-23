package com.project.spring.service;

import java.util.List;

import com.project.spring.model.Book;

public interface BookService {
	
	//save the record
		long save(Book book);
		
		//get a single record
		Book get(long id);
		
		//get all the record
		List<Book> list();
		
		//update the record
		void update(long id, Book book);
		
		//delete a record
		void delete(long id);
}
