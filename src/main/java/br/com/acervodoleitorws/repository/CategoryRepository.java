package br.com.acervodoleitorws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.acervodoleitorws.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
