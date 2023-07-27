package com.example.payment.model;

import org.springframework.data.mongodb.core.mapping.Document;

public class Book {

	private String bookId;
	
	private String bookName;
	
	private String bookType;
	
	private float rating;
	
	private double stock;

	private double price;
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String bookId, String bookName, String bookType, float rating, double stock, double price) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookType = bookType;
		this.rating = rating;
		this.stock = stock;
		this.price = price;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public double getStock() {
		return stock;
	}
	public void setStock(double stock) {
		this.stock = stock;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
