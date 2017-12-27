package br.com.acervodoleitorws.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.acervodoleitorws.event.ResourceCreatedEvent;

@Component
public class ResourceCreatedListener implements ApplicationListener<ResourceCreatedEvent>{

	@Override
	public void onApplicationEvent(ResourceCreatedEvent resourceCreatedEvent) {
		HttpServletResponse response = resourceCreatedEvent.getResponse();
		Long uid = resourceCreatedEvent.getUid();
		String isbn = resourceCreatedEvent.getIsbn();		
		String resource = uid != null ? uid.toString() : isbn;
		addHeaderLocation(response, resource);
	}

	private void addHeaderLocation(HttpServletResponse response, Object resource) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{uid}")
				.buildAndExpand(resource.toString()).toUri();
		response.setHeader("Location", uri.toASCIIString());	
	}

}
