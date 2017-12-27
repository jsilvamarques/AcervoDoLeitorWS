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
import br.com.acervodoleitorws.model.Publisher;
import br.com.acervodoleitorws.repository.PublisherRepository;
import br.com.acervodoleitorws.service.PublisherService;

@RestController
@RequestMapping("/publisher")
public class PublisherResource {

	@Autowired
	private PublisherRepository publisherRepository;
	
	@Autowired
	private PublisherService publisherService;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@PostMapping
	public ResponseEntity<Publisher> save(@Valid @RequestBody Publisher publisher, HttpServletResponse response){		
		Publisher publisherSaved = this.publisherRepository.save(publisher);
		applicationEventPublisher.publishEvent(new ResourceCreatedEvent(this, response, publisher.getUid()));
		return ResponseEntity.status(HttpStatus.CREATED).body(publisherSaved);
	}
	
	@GetMapping
	public List<Publisher> findAll(){
		return this.publisherRepository.findAll();		
	}
	
	@GetMapping("/{uid}")
	public ResponseEntity<Publisher> findByUid(@PathVariable Long uid){
		Publisher publisher = this.publisherRepository.findOne(uid);		
		return publisher != null ? ResponseEntity.ok(publisher) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{uid}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long uid){
		this.publisherRepository.delete(uid);
	}
	
	@PutMapping("/{uid}")
	public ResponseEntity<Publisher> update(@PathVariable Long uid, @Valid @RequestBody Publisher publisher){
		try {
			Publisher publisherSaved = this.publisherService.update(uid, publisher);
			return ResponseEntity.ok(publisherSaved);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
