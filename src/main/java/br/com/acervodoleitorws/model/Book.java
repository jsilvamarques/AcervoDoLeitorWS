package br.com.acervodoleitorws.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "book")
@JsonInclude(Include.NON_NULL)
public class Book implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String isbn;
	
	@NotNull
	private String name;
	
	@NotNull
	private String cover;
	
	@NotNull
	private String thumbnail;
	
	@NotNull
	private String description;
	
	@NotNull
	private Integer page;
	
	@NotNull
	private Integer releaseYear;
	
	private Integer views;
	
	@Column(name = "creation_date")
	private LocalDate creationDate;
	
	@ManyToOne
	private Publisher publisher;
	
	@ManyToMany
	@JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "isbn_book"), inverseJoinColumns = @JoinColumn(name = "uid_author"))
	private List<Author> authors;
	
	@ManyToMany
	@JoinTable(name = "book_category", joinColumns = @JoinColumn(name = "isbn_book"), inverseJoinColumns = @JoinColumn(name = "uid_category"))
	private List<Category> categories;

	public Book() {
	}
	
	public Book(String isbn, String name, String cover) {
		this.isbn = isbn;
		this.name = name;
		this.cover = cover;
	}
	
	public Book(String name, String thumbnail) {
		this.name = name;
		this.thumbnail = thumbnail;
	}

	public Book(String isbn, String name, String cover, String thumbnail, String description, Integer page,
			Integer releaseYear, Integer views, LocalDate creationDate, Publisher publisher, List<Author> authors,
			List<Category> categories) {
		this.isbn = isbn;
		this.name = name;
		this.cover = cover;
		this.thumbnail = thumbnail;
		this.description = description;
		this.page = page;
		this.releaseYear = releaseYear;
		this.views = views;
		this.creationDate = creationDate;
		this.publisher = publisher;
		this.authors = authors;
		this.categories = categories;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", name=" + name + ", cover=" + cover + "]";
	}
}
