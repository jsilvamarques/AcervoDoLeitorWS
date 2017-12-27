package br.com.acervodoleitorws.repository.book;

import java.util.List;

import br.com.acervodoleitorws.model.Book;

public interface BookRepositoryQuery {

	public List<Book> getSimpleBooks();
	
	public List<Book> getBooks();
	
	public List<Book> getSimpleBooksByViews();
	
	public List<Book> getSimpleBooksByName(String name);
	
	public List<Book> getSimpleBooksByCategory(Long uid);
	
	public void updateView(String isbn);
	
	
}
