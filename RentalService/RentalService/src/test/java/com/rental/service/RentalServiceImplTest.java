package com.rental.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rental.exception.RentalInvalidException;
import com.rental.model.Rental;

@SpringBootTest
public class RentalServiceImplTest {
	
	
	@Autowired
	RentalServiceImpl rentalServiceImpl;

	
	@Test
	void getRentalById() throws RentalInvalidException{
        Rental r = rentalServiceImpl.getRentalById("202");
		
	}
	
	@Test
	void findById() throws RentalInvalidException{
		 Rental r = rentalServiceImpl.findById("103");
	        String bookName=r.getBookRated();
	        assertFalse(bookName.isEmpty());
	    
	}

	@Test
	void getByUsername() throws RentalInvalidException{
		List<Rental> r = rentalServiceImpl.getByUsername(null);
	   	 assertNotNull(r);
	}
	
	 @Test
	 void viewAll() throws RentalInvalidException{
	     List<Rental> r = rentalServiceImpl.viewAll();
	     System.out.println(r);
	     assertFalse(r.isEmpty());
	 }
	
}
