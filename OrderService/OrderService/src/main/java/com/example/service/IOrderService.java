package com.example.service;

import java.util.List;

import com.example.exception.OrderInvalidException;
import com.example.model.Order;

public interface IOrderService {

	public Order addOrder(String username, Order order) throws OrderInvalidException;

	public Order getOrderById(String orderId) throws OrderInvalidException;

	public Order deleteOrder(String orderId) throws OrderInvalidException;

	public List<Order> viewAll() throws OrderInvalidException;

}
