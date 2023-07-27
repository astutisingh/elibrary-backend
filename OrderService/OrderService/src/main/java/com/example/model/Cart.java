package com.example.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Cart")
public class Cart {
	
	@Transient
	public static final String SEQUENCE_NAME = "cart_sequence";
	
	@Id
	private String cartId;
	private List<CartItem> cartItemList;
	private double totalAmount;
	/*
	 * Status - InCart - OutCart
	 */
	private String status;
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cart(String cartId, List<CartItem> cartItemList, double totalAmount, String status) {
		super();
		this.cartId = cartId;
		this.cartItemList = cartItemList;
		this.totalAmount = totalAmount;
		this.status = status;
	}
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public List<CartItem> getCartItemList() {
		return cartItemList;
	}
	public void setCartItemList(List<CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
