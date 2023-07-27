package com.rental.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rental.model.Rental;

@SpringBootTest
public class RentalRepositoryTest {
	


    @Autowired
    RentalRepository rentalRepository;

    @Test
    void findByRentalId() {
        Optional< Rental> s = rentalRepository.findByRentalId("203");
        assertFalse(s.isEmpty());
    }

    @Test
    void existsByRentalId() {

        Boolean s = rentalRepository.existsByRentalId("203");
        assertTrue(s);
    }
    
    @Test
    void findAll(){
        List<Rental> l = rentalRepository.findAll();
        System.out.println(l);
        assertFalse(l.isEmpty());
    }
    

    
    @Test
    void getBookIdByRentalId() {
   	 Rental r = rentalRepository.getBookIdByRentalId("202");
   	 assertNotNull(r);

   }
    
    @Test
    void getByUsername() {
   	 List<Rental> l = rentalRepository.getByUsername(null);
   	 assertNotNull(l);
   	 
   	 
    }
    
    

}
