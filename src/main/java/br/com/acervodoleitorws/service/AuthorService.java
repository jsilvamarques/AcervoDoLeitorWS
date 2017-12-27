package br.com.acervodoleitorws.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acervodoleitorws.model.Author;
import br.com.acervodoleitorws.repository.AuthorRepository;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	public Author update(Long uid, Author author){
		Author authorSaved = findAuthorExisting(uid);		
		BeanUtils.copyProperties(author, authorSaved, "uid");
		return this.authorRepository.save(authorSaved);		
	}

	private Author findAuthorExisting(Long uid) {
		Author authorSaved = this.authorRepository.findOne(uid);
		
		if(authorSaved == null){
			throw new IllegalArgumentException();
		}
		
		return authorSaved;
	}

}
