package br.com.acervodoleitorws.resource;

import java.util.List;

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
import br.com.acervodoleitorws.model.Category;
import br.com.acervodoleitorws.repository.CategoryRepository;
import br.com.acervodoleitorws.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryResource {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<Category> sabe(@Valid @RequestBody Category category, HttpServletResponse response){
		Category categorySaved = this.categoryRepository.save(category);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, categorySaved.getUid()));
		return ResponseEntity.status(HttpStatus.CREATED).body(category);		
	}

	@GetMapping
	public List<Category> findAll(){
		return this.categoryRepository.findAll();
	}
	
	@GetMapping("/{uid}")
	public ResponseEntity<Category> findByUid(@PathVariable Long uid){
		Category category = this.categoryRepository.findOne(uid);
		return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{uid}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long uid){
		this.categoryRepository.delete(uid);
	}
	
	@PutMapping("/{uid}")
	public ResponseEntity<Category> update(@PathVariable Long uid, @Valid @RequestBody Category category){
		try {
			Category categorySaved = this.categoryService.update(uid, category);
			return ResponseEntity.ok(categorySaved);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
