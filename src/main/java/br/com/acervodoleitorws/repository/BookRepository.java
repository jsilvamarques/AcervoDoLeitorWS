package br.com.acervodoleitorws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.acervodoleitorws.model.Book;
import br.com.acervodoleitorws.repository.book.BookRepositoryQuery;

@Repository
public interface BookRepository extends JpaRepository<Book, String>, BookRepositoryQuery{

}
