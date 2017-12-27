package br.com.acervodoleitorws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.acervodoleitorws.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{

}
