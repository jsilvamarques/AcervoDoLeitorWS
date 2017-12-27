package br.com.acervodoleitorws;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config.properties")
@ConfigurationProperties
public class ConfigProperties {
	
	private String urlBase;	
	private String resourceBook;
	private String resourcePerfil;
	private String resourceReview;	
	private String prefixThumbnail;
	
	public String getUrlBase() {
		return urlBase;
	}
	public void setUrlBase(String urlBase) {
		this.urlBase = urlBase;
	}
	public String getResourceBook() {
		return resourceBook;
	}
	public void setResourceBook(String resourceBook) {
		this.resourceBook = resourceBook;
	}
	public String getResourcePerfil() {
		return resourcePerfil;
	}
	public void setResourcePerfil(String resourcePerfil) {
		this.resourcePerfil = resourcePerfil;
	}
	public String getResourceReview() {
		return resourceReview;
	}
	public void setResourceReview(String resourceReview) {
		this.resourceReview = resourceReview;
	}
	public String getPrefixThumbnail() {
		return prefixThumbnail;
	}
	public void setPrefixThumbnail(String prefixThumbnail) {
		this.prefixThumbnail = prefixThumbnail;
	}
}
