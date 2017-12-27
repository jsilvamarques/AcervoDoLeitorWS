package br.com.acervodoleitorws.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "review")
@JsonInclude(Include.NON_NULL)
public class Review implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;
	
	private String review;
	
	private String veredict;
	
	private Long evaluation;
	
	private Integer views;
	
	@Column(name = "publication_date")
	private LocalDate publicationDate;
	
	@OneToOne
	@JoinColumn(name = "isbn_book", referencedColumnName = "isbn" )
	private Book book; 
	
	@Transient
	private Like like;

	public Review() {
	}

	public Review(Long uid, String review, String veredict, Long evaluation, Integer views, LocalDate publicationDate,
			Book book) {
		this.uid = uid;
		this.review = review;
		this.veredict = veredict;
		this.evaluation = evaluation;
		this.views = views;
		this.publicationDate = publicationDate;
		this.book = book;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getVeredict() {
		return veredict;
	}

	public void setVeredict(String veredict) {
		this.veredict = veredict;
	}

	public Long getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Long evaluation) {
		this.evaluation = evaluation;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public LocalDate getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Like getLike() {
		return like;
	}

	public void setLike(Like like) {
		this.like = like;
	}
	
}
