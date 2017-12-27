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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import br.com.acervodoleitorws.ConfigProperties;
import br.com.acervodoleitorws.dto.ImageDTO;
import br.com.acervodoleitorws.event.ResourceCreatedEvent;
import br.com.acervodoleitorws.model.Book;
import br.com.acervodoleitorws.repository.BookRepository;
import br.com.acervodoleitorws.service.BookService;
import br.com.acervodoleitorws.util.ImageUtil;

@RestController
@RequestMapping("/book")
public class BookResource {
	
	@Autowired
	private BookRepository bookRespository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ImageUtil imageUtil;
	
	@Autowired
	private ConfigProperties configProperties;
	
	@PostMapping
	public ResponseEntity<Book> save(@Valid @RequestBody Book book, HttpServletResponse response){
		Book bookSaved = this.bookRespository.save(book);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, book.getIsbn()));
		return ResponseEntity.status(HttpStatus.CREATED).body(bookSaved);
	}
	
	@PutMapping("/{isbn}")
	public ResponseEntity<Book> update(@PathVariable String isbn, @Valid @RequestBody Book book){
		try {
			Book bookSaved = this.bookService.update(isbn, book);
			return ResponseEntity.ok(bookSaved);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}	
	
	@GetMapping("/{isbn}")
	public ResponseEntity<Book> findByIsbn(@PathVariable String isbn){
		Book book = this.bookService.getBookByIsbn(isbn);
		return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{isbn}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String isbn) {
		this.bookRespository.delete(isbn);
	}

	@GetMapping
	public List<Book> getAllBooks(){
		return this.bookRespository.findAll();
	}

	@GetMapping("/simple")
	public List<Book> getSimpleBooks(){
		return this.bookRespository.getSimpleBooks();
	}
	
	@GetMapping("/simple/views")
	public List<Book> findSimpleBooksByView(){
		return this.bookRespository.getSimpleBooksByViews();
	}
	
	@GetMapping("/simple/{name}")
	public List<Book> findSimpleBooksByName(@PathVariable String name){
		return this.bookRespository.getSimpleBooksByName(name);
	}
	
	@GetMapping("/category/simple/{uid}")
	public List<Book> findSImpleBooksByCategory(@PathVariable Long uid){
		return this.bookRespository.getSimpleBooksByCategory(uid);
	}
	
	@PostMapping("/upload")
	public DeferredResult<ImageDTO> upload(@RequestParam("files[]") MultipartFile[] files) {
		DeferredResult<ImageDTO> resultado = new DeferredResult<>();
		resultado = imageUtil.uploadImage(files, configProperties.getResourceBook());		
		return resultado;
	}
	
	@GetMapping("/image/{name:.*}")
	public byte[] getImage(@PathVariable String name) {
		return imageUtil.getImage(name, configProperties.getResourceBook());
	}
}
