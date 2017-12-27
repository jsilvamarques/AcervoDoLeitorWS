package br.com.acervodoleitorws.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acervodoleitorws.enumtype.ResourceType;
import br.com.acervodoleitorws.model.Review;
import br.com.acervodoleitorws.repository.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private LikeService likeService;
	
	public Review getReviewByUid(Long uid, String uidProfile){
		this.reviewRepository.updateViews(uid);
		Review review = reviewRepository.findOne(uid);
		review.setLike(likeService.getLikeByIdResourceAndIdProfile(uid.toString(), uidProfile, ResourceType.REVIEW.toString()));
		return review;
	}
	
	public Review update(Long uid, Review review) {
		Review reviewSaved = this.reviewRepository.findOne(uid);
		if(reviewSaved == null) {
			throw new IllegalArgumentException();
		}
		BeanUtils.copyProperties(review, reviewSaved, "uid");
		return this.reviewRepository.save(reviewSaved);
	}

}
