package br.com.acervodoleitorws.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acervodoleitorws.model.Publisher;
import br.com.acervodoleitorws.repository.PublisherRepository;

@Service
public class PublisherService {

	@Autowired
	private PublisherRepository publisherRepository;
	
	public Publisher update(Long uid, Publisher publisher){
		Publisher publisherSaved = findPublisherExisting(uid);		
		BeanUtils.copyProperties(publisher, publisherSaved, "uid");
		return this.publisherRepository.save(publisherSaved);		
	}

	private Publisher findPublisherExisting(Long uid) {
		Publisher publisherSaved = this.publisherRepository.findOne(uid);		
		if(publisherSaved == null){
			throw new IllegalArgumentException();
		}
		
		return publisherSaved;
	}

}
