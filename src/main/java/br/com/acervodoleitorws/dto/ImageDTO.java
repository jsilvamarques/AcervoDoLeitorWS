package br.com.acervodoleitorws.dto;

public class ImageDTO {
	
	private String name;
	private String thumbnail;
	private String contentType;

	public ImageDTO(String name, String thumbnail, String contentType) {
		this.name = name;
		this.thumbnail = thumbnail;
		this.contentType = contentType;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
}
