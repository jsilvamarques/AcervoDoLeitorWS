package br.com.acervodoleitorws.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.acervodoleitorws.event.ResourceCreatedEvent;
import br.com.acervodoleitorws.model.Review;
import br.com.acervodoleitorws.repository.ReviewRepository;
import br.com.acervodoleitorws.service.ReviewService;

@RestController
@RequestMapping("review")
public class ReviewResource {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping
	public List<Review> getAllReviews(){
		return this.reviewRepository.findAll();
	}
	
	@GetMapping("/{uid}")
	public ResponseEntity<Review> findByuid(@PathVariable Long uid, HttpServletRequest request){
		String uidProfile = request.getHeader("uidProfile");
		Review review = this.reviewService.getReviewByUid(uid, uidProfile);
		return review != null ? ResponseEntity.ok(review) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/simple")
	public List<Review> getSimpleReviews(){
		return this.reviewRepository.getSimpleReviews();
	}
	
	@GetMapping("/simple/views")
	public List<Review> getSimpleReviewsByViews(){
		return this.reviewRepository.getSimpleReviewsByViews();
	}
	
	@GetMapping("/simple/{name}")
	public List<Review> findSimpleReviewsByName(@PathVariable String name){
		return this.reviewRepository.getSimpleReviewsByName(name);
	}
	
	@PostMapping
	public ResponseEntity<Review> save(@Valid @RequestBody Review review, HttpServletResponse response){
		Review reviewSaved = this.reviewRepository.save(review);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, review.getUid()));
		return ResponseEntity.status(HttpStatus.CREATED).body(reviewSaved);
	}
	
	@PutMapping("/{uid}")
	public ResponseEntity<Review> update(@PathVariable Long uid, @Valid @RequestBody Review review){
		try {
			Review reviewSaved = this.reviewService.update(uid, review);
			return ResponseEntity.ok(reviewSaved);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{uid}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long uid){
		this.reviewRepository.delete(uid);
	}
}
