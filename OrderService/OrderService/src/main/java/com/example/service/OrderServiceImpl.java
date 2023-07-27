package com.example.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.exception.OrderInvalidException;
import com.example.model.CartItem;
import com.example.model.Order;
import com.example.repository.OrderRepository;
import com.example.sequence.SequenceGeneratorServiceForOrder;

@Service
public class OrderServiceImpl implements IOrderService {
	

	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private SequenceGeneratorServiceForOrder forOrder;


	@Override
	public Order addOrder(String username,Order order) throws OrderInvalidException{
		LocalDate orderDate = LocalDate.now();
		order.setOrderId(String.valueOf(forOrder.generateSequence(Order.SEQUENCE_NAME)));
		CartItem[] cartItem = restTemplate.getForObject("http://localhost:7000/cart/cartItems/"+username, CartItem[].class);
		order.setUsername(username);
		order.setCart(cartItem);
		order.setOrderDate(orderDate);
		order.setDeliveryDate(orderDate);
		order.setTotal(totalAmount(username));
		restTemplate.delete("http://localhost:7000/cart/delete/"+username);
		return orderRepository.save(order);
	}
	
	private Double totalAmount(String username) {
		double amount = 0;
		CartItem[] cartItem = restTemplate.getForObject("http://localhost:7000/cart/cartItems/"+username, CartItem[].class);
		for(int i = 0; i<cartItem.length;i++)
		{
			amount = amount + (cartItem[i].getQuantity() * cartItem[i].getTotal());
		}
		return amount;
	}

	@Override
	public Order getOrderById(String orderId) throws OrderInvalidException {
		Optional<Order> order = orderRepository.findById(orderId);
		if (order.isPresent()) {
			return order.get();
		}
		throw new OrderInvalidException("Order is not found");
	}

	@Override
	public Order deleteOrder(String orderId) throws OrderInvalidException {
		Order order = getOrderById(orderId);
		orderRepository.delete(order);
		return order;
	}

	@Override
	public List<Order> viewAll() throws OrderInvalidException {
		return orderRepository.findAll();
	}

	public List<Order> getOrderByUsername(String username) {
		
		
		return orderRepository.findByUsername(username);
	}
	
	public Order updateOrderBystatus(String orderId, Order order) throws OrderInvalidException{
		Order order1 = orderRepository.findByOrderId(orderId);
		if(order1!=null) {
			order1.setDeliveryDate(order.getDeliveryDate());
			order1.setStatus(order.getStatus());
		}else {
			throw new OrderInvalidException("Invalid OrderId");
		}
		return orderRepository.save(order1);
	}
	
	public Order updateOrderByPaymentstatus(String orderId, Order order) throws OrderInvalidException{
		Order order1 = orderRepository.findByOrderId(orderId);
		if(order1!=null) {
			order1.setPaymentStatus(order.getPaymentStatus());
		}else {
			throw new OrderInvalidException("Invalid OrderId");
		}
		return orderRepository.save(order1);
	}

}
