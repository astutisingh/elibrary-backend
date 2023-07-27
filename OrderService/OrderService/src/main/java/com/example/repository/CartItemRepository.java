package com.example.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.model.CartItem;
import com.example.model.Order;

@Repository
public interface CartItemRepository extends MongoRepository<CartItem, String>{
	
CartItem findByCartItemId(String cartItemId);
	
	void deleteByUsername(String username);
	
	
	List<CartItem> findByUsername(String username);

}
