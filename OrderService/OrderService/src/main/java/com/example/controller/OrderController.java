package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.OrderInvalidException;
import com.example.model.Order;
import com.example.service.OrderServiceImpl;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class OrderController {

	@Autowired
	private OrderServiceImpl orderServiceImpl;

	@PostMapping("/addOrder/{username}")
	public ResponseEntity<Order> addOrder(@PathVariable String username, @RequestBody Order order) {
		try {
			Order savedOrder = orderServiceImpl.addOrder(username, order);
			return ResponseEntity.ok(savedOrder);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	

	@GetMapping("/order/{orderId}")
	public ResponseEntity<Order> getOrderById(@PathVariable String orderId) {
		try {
			Order order = orderServiceImpl.getOrderById(orderId);
			return ResponseEntity.ok(order);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@GetMapping("/orderPerUser/{username}")
	public ResponseEntity<List<Order>> getOrderByUsername(@PathVariable String username) {
		try {
			List<Order> order = orderServiceImpl.getOrderByUsername(username);
			return ResponseEntity.ok(order);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@DeleteMapping("/delete/{orderId}")
	public ResponseEntity<Order> deleteOrder(@PathVariable String orderId) {
		try {
			Order order = orderServiceImpl.deleteOrder(orderId);
			return ResponseEntity.ok(order);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/viewAll")
	public ResponseEntity<List<Order>> viewAll() {
		try {
			List<Order> orders = orderServiceImpl.viewAll();
			return ResponseEntity.ok(orders);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@PutMapping("/updateOrder/{orderId}")
	public  ResponseEntity<Order> updateOrder(@PathVariable String orderId, @RequestBody Order order) throws OrderInvalidException{
		try {
			Order updateOrder = orderServiceImpl.updateOrderBystatus(orderId, order);
			return ResponseEntity.ok(updateOrder);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@PutMapping("/updateOrderPerPay/{orderId}")
	public  ResponseEntity<Order> updateOrderPerStatus(@PathVariable String orderId, @RequestBody Order order) throws OrderInvalidException{
		try {
			Order updateOrder = orderServiceImpl.updateOrderByPaymentstatus(orderId, order);
			return ResponseEntity.ok(updateOrder);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
