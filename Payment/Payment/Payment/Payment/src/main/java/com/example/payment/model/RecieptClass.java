package com.example.payment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="reciept")
public class RecieptClass {
	
	@Id
	private String paymentId;
	private String username;
	private  Order order;
	private double amount;
	private String status;
	public RecieptClass() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RecieptClass(String paymentId, String username, Order order, double amount, String status) {
		super();
		this.paymentId = paymentId;
		this.username = username;
		this.order = order;
		this.amount = amount;
		this.status = status;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
