package br.com.acervodoleitorws.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acervodoleitorws.model.Book;
import br.com.acervodoleitorws.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public Book getBookByIsbn(String isbn) {
		this.bookRepository.updateView(isbn);
		return this.bookRepository.findOne(isbn);
	}
	
	public Book update(String isbn, Book book) {
		Book bookSaved = this.bookRepository.findOne(isbn);
		if(bookSaved == null) {
			throw new IllegalArgumentException();
		}
		BeanUtils.copyProperties(book, bookSaved, "isbn");
		return this.bookRepository.save(bookSaved);
	}
}
