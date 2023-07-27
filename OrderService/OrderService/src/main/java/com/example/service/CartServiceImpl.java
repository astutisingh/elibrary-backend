package com.example.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.exception.CartInvalidException;
import com.example.model.Book;
import com.example.model.CartItem;
import com.example.model.User;
import com.example.repository.CartItemRepository;
import com.example.sequence.SequenceGeneratorServiceForCartItem;

@Service
public class CartServiceImpl {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(CartServiceImpl.class);
	
	@Autowired
	private SequenceGeneratorServiceForCartItem sequenceGeneratorServiceForCartItem;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CartItemRepository repository;
	
	
	public CartItem addCartItems(String username,String bookId, CartItem cartItem) throws CartInvalidException
	{
		Book books = restTemplate.getForObject("http://localhost:8081/book/viewBookByBookId/"+bookId, Book.class);
		LOGGER.info("Get BookId "+books.getBookId());
		cartItem.setUsername(username);
		cartItem.setCartItemId(String.valueOf(sequenceGeneratorServiceForCartItem.generateSequence(CartItem.SEQUENCE_NAME)));
		cartItem.setBook(books);
		cartItem.setTotal(getTotal(cartItem));
		return repository.save(cartItem);
	}
	
	public CartItem updateCartItems(String username, String cartItemId, CartItem cartItem) throws CartInvalidException
	{
		CartItem item = repository.findByCartItemId(cartItemId);
		if(item!=null)
		{
			item.setQuantity(cartItem.getQuantity());
			item.setTotal(getTotal(item));
			item  = repository.save(item);
		}
		else
		{
			throw new CartInvalidException("error occured while updating!");
		}
		return item;
	}
	
	private Double getTotal(CartItem cartItem) {
		double total = cartItem.getQuantity() * cartItem.getBook().getPrice();
		return total;
	}
	
	public List<CartItem> getCartItemByUsername(String username)
	{
		return repository.findByUsername(username);
	}
	
	public void deleteCart(String username) {
		repository.deleteByUsername(username);
	}
	

}
