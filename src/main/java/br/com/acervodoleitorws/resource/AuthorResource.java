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
import br.com.acervodoleitorws.model.Author;
import br.com.acervodoleitorws.repository.AuthorRepository;
import br.com.acervodoleitorws.service.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorResource {

	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<Author> save(@Valid @RequestBody Author author, HttpServletResponse response){
		Author authorSaved = this.authorRepository.save(author);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, authorSaved.getUid()));
		return ResponseEntity.status(HttpStatus.CREATED).body(authorSaved);
	}
	
	@GetMapping
	public List<Author> findAll(){
		return this.authorRepository.findAll();
	}
	
	@GetMapping("/{uid}")
	public ResponseEntity<Author> findByUid(@PathVariable Long uid){
		Author author = this.authorRepository.findOne(uid);
		return author != null ? ResponseEntity.ok(author) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{uid}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long uid){
		this.authorRepository.delete(uid);		
	}
	
	@PutMapping("/{uid}")
	public ResponseEntity<Author> update(@PathVariable Long uid, @Valid @RequestBody Author author){
		try {
			Author authorSaved = this.authorService.update(uid, author);
			return ResponseEntity.ok(authorSaved);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
