package com.example.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Order")
public class Order {
	
	@Transient
	public static final String SEQUENCE_NAME = "order_sequence";
	
	@Id
	private String orderId;
	private String username;
	private CartItem[] cart;
	private LocalDate orderDate;
	private LocalDate deliveryDate;
	private String status;
	private String paymentStatus;
	private double total;

	public Order() {
		super();
	}

	public Order(String orderId, String username, CartItem[] cart, LocalDate orderDate, LocalDate deliveryDate,
			String status, String paymentStatus,double total) {
		super();
		this.orderId = orderId;
		this.username = username;
		this.cart = cart;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.status = status;
		this.paymentStatus = paymentStatus;
		this.total = total;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public CartItem[] getCart() {
		return cart;
	}

	public void setCart(CartItem[] cart) {
		this.cart = cart;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getStatus() {
		return status;
	}
	


	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	
	

	}