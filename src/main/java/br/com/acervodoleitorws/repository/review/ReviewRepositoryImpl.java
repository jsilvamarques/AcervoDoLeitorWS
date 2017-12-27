package br.com.acervodoleitorws.repository.review;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.acervodoleitorws.model.Book;
import br.com.acervodoleitorws.model.Review;

@Transactional
public class ReviewRepositoryImpl implements ReviewRepositoryQuery{

	@Autowired
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Review> getSimpleReviews() {
		Query query = em.createNativeQuery("select r.uid, b.name, b.thumbnail from review r, book b where r.isbn_book = b.isbn order by r.publication_date desc limit 10;");
		return this.mountListSimpleReviews(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Review> getSimpleReviewsByViews() {
		Query query = em.createNativeQuery("select r.uid, b.name, b.thumbnail, r.views from review r, book b where r.isbn_book = b.isbn order by r.publication_date desc limit 10;");
		return this.mountListSimpleReviews(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Review> getSimpleReviewsByName(String name) {
		Query query = em.createNativeQuery("select r.uid, b.name, b.thumbnail from review r, book b where r.isbn_book = b.isbn and lower(b.name) like :name order by r.publication_date desc limit 10;");
		query.setParameter("name", "%" + name.toLowerCase() + "%");		
		return this.mountListSimpleReviews(query.getResultList());
	}

	@Override
	public void updateViews(Long uid) {		
		Query query = em.createNativeQuery("update review set views = views + 1 where uid = :uid");
		query.setParameter("uid", uid);		
		query.executeUpdate();	
	}

	
	private List<Review> mountListSimpleReviews(List<Object[]> result) {
		List<Review> reviews = new ArrayList<Review>();
		for (int i = 0; i < result.size(); i++) {
			Object[] resultObject = result.get(i);
			Review review = new Review();
			review.setUid(Long.parseLong(resultObject[0].toString()));
			review.setBook(new Book(resultObject[1].toString(), resultObject[2].toString()));
			if(resultObject.length == 4 ) {
				review.setViews(Integer.parseInt(resultObject[3].toString()));
			}
			
			reviews.add(review);
		}
		
		return reviews;
	}
}
