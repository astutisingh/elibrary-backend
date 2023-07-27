package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CartItem")
public class CartItem {
	
	@Transient
	public static final String SEQUENCE_NAME = "cartitem_sequence";
	
	@Id
	private String cartItemId;
	private String username;
	private Book book;
	private int quantity;
	private double total;
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartItem(String cartItemId, String username, Book book, int quantity, double total) {
		super();
		this.cartItemId = cartItemId;
		this.username = username;
		this.book = book;
		this.quantity = quantity;
		this.total = total;
	}
	public String getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(String cartItemId) {
		this.cartItemId = cartItemId;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	

}
