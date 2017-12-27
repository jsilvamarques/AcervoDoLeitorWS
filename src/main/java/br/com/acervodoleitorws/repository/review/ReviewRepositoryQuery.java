package br.com.acervodoleitorws.repository.review;

import java.util.List;

import br.com.acervodoleitorws.model.Review;

public interface ReviewRepositoryQuery {

	public List<Review> getSimpleReviews();

	public List<Review> getSimpleReviewsByViews();

	public List<Review> getSimpleReviewsByName(String name);

	public void updateViews(Long uid);

}
