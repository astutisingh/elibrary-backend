package com.rental.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "Rental")
public class Rental {
	 
	@Transient
	public static final String SEQUENCE_NAME = "rental_sequence";
	
    @Id
	private String rentalId;
    private String username;
	private Book books;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	private String startDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	private String endDate;
    private String status;
    private String bookRated;
    
    
	public Rental() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    public Rental(String rentalId, Book books, String startDate, String endDate, String status, String username, String bookRated) {
		super();
		this.rentalId = rentalId;
		this.username = username;
		this.books = books;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.bookRated = bookRated;
	}
    
    




	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRentalId() {
		return rentalId;
	}
	public void setRentalId(String rentalId) {
		this.rentalId = rentalId;
	}
	
	public Book getBooks() {
		return books;
	}

	public void setBooks(Book books) {
		this.books = books;
	}

	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getBookRated() {
		return bookRated;
	}

	public void setBookRated(String bookRated) {
		this.bookRated = bookRated;
	}	
	
}
