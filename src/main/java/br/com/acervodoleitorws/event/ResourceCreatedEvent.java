package br.com.acervodoleitorws.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class ResourceCreatedEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1L;

	private HttpServletResponse response;
	
	private Long uid;
	
	private String isbn;
	
	public ResourceCreatedEvent(Object source, HttpServletResponse response, Long uid) {
		super(source);
		this.response = response;
		this.uid = uid;
	}
	
	public ResourceCreatedEvent(Object source, HttpServletResponse response, String isbn) {
		super(source);
		this.response = response;
		this.isbn = isbn;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Long getUid() {
		return uid;
	}

	public String getIsbn() {
		return isbn;
	}
}
