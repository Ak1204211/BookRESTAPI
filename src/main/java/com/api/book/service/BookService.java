package com.api.book.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.book.dao.BookRepository;
import com.api.book.entities.Book;

@Service
public class BookService {
	
	@Autowired
	private BookRepository repo;

	private static ArrayList<Book> list = new ArrayList<Book>();

	static {
//		list.add(new Book(1, "Java", "Aryan"));
//		list.add(new Book(2, "Python", "Lucky"));
//		list.add(new Book(3, "Ruby", "Hershel"));
	}

	public List<Book> getAllBooks() {
		List<Book> list= (List<Book>)repo.findAll();
		return list;
	}

	public Book getBookById(int id) {
//		Book book = list.stream().filter(e -> e.getId() == id).findFirst().get();
		Book book=repo.findById(id);
		
		return book;
	}

	public Book addBook(Book book) {
//		list.add(book);
		
		Book book1 = repo.save(book);
		return book1;
	}

	public void deleteBook(int id) {
//		 list = (ArrayList) list.stream().filter(book -> book.getId()!=id).collect(Collectors.toList());
		repo.deleteById(id);
	}
	
	
	public void updateBook(Book book,int id) {
//		list = (ArrayList)list.stream().map(b->{
//			if(b.getId()==id) {
//				b.setName(book.getName());
//				b.setAuthor(book.getAuthor());
//			}
//			return b;
//		}).collect(Collectors.toList());
		
		book.setId(id);
		Book book1 = repo.save(book);
		
		
	}

}
