package com.example.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>{
	
Order findByOrderId(String orderId);
	
	void deleteByOrderId(String orderId);
	
	List<Order> findByUsername(String username);

}
