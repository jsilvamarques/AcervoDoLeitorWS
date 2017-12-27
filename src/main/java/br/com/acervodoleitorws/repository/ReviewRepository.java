package br.com.acervodoleitorws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.acervodoleitorws.model.Review;
import br.com.acervodoleitorws.repository.review.ReviewRepositoryQuery;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryQuery{

}
