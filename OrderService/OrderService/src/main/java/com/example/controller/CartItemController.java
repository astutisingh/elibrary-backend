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
import org.springframework.web.server.ResponseStatusException;

import com.example.exception.CartInvalidException;
import com.example.model.CartItem;
import com.example.repository.CartItemRepository;
import com.example.service.CartServiceImpl;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class CartItemController {
	
	@Autowired
	private CartServiceImpl cartServiceImpl;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@PostMapping("/item/{username}/{bookId}")
	public ResponseEntity<CartItem> addCartItem(@PathVariable String username, @PathVariable("bookId") String bookId, @RequestBody CartItem cartItems) throws CartInvalidException
	{
		try {
		CartItem cartItem = cartServiceImpl.addCartItems(username, bookId, cartItems);
		return new ResponseEntity<>(cartItem, HttpStatus.OK);
	    }
		catch (Exception e)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update/{username}/{cartItemId}")
	public ResponseEntity<CartItem> updateItem(@PathVariable String username, @PathVariable String cartItemId,@RequestBody CartItem cartItems) throws CartInvalidException
	{
		try {
			CartItem updateditem = cartServiceImpl.updateCartItems(username, cartItemId, cartItems);
			return new ResponseEntity<>(updateditem, HttpStatus.OK);
		}
		catch (Exception e)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/cartItems/{username}")
	public ResponseEntity<List<CartItem>> getCartItemsByUsername(@PathVariable String username){
		try {
			List<CartItem> cartItem = cartServiceImpl.getCartItemByUsername(username);
			return new ResponseEntity<>(cartItem, HttpStatus.OK);
		}
		catch (Exception e)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{username}")
	public void deleteCart(@PathVariable String username) {
		cartItemRepository.deleteByUsername(username);
	}

}
