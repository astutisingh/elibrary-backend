package com.rental.controller;

import java.util.List;
import java.util.Optional;

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

import com.rental.exception.RentalInvalidException;
import com.rental.model.Rental;
import com.rental.service.RentalServiceImpl;

@RestController
@RequestMapping("/rental")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RentalController {
	
	@Autowired
	private RentalServiceImpl rentalServiceImpl;

	

	@PostMapping("/addRental/{bookId}")
	public ResponseEntity<Rental> addRental(@PathVariable String bookId,@RequestBody Rental rental) {
	    try {
	        Rental createdRentalId = rentalServiceImpl.addRental(bookId,rental);
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdRentalId);
	    } catch (Exception e) {
	        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	@GetMapping("/viewRental/{rentalId}")
    public ResponseEntity<Rental> getRentalById(@PathVariable("rentalId") String rentalId){
		try {
	        Rental rental = rentalServiceImpl.getRentalById(rentalId);
	        if (!rental.equals(null)) {
	            return new ResponseEntity<>(rental, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@GetMapping("/viewbyUsername/{username}")
    public ResponseEntity<List<Rental>> getRentalByName(@PathVariable String username){
		try {
	        List<Rental> rental = rentalServiceImpl.getByUsername(username);
	        if (!rental.equals(null)) {
	            return new ResponseEntity<>(rental, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


    @PutMapping("/update/{rentalId}")
	public ResponseEntity<Rental> updateRental(@PathVariable String rentalId, @RequestBody Rental rental) {
	    try {
	        Rental updatedRental = rentalServiceImpl.updateRental(rentalId, rental);
	        
	        return ResponseEntity.ok(updatedRental);
	    } catch (Exception e) {
	        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
	    }	
    }
	    
	    @GetMapping("/viewAll")
	    public List<Rental> viewAllRentals() {
	    	return rentalServiceImpl.viewAll();
	    }
	    
	
	    @PostMapping("/rating/{rentalId}/{rating}")
	    public ResponseEntity<Rental> addRating(@PathVariable String rentalId, @PathVariable float rating, @RequestBody Rental rental) throws RentalInvalidException
	    {
	    	try {
	            rentalServiceImpl.addRatingForBooks(rentalId,rating, rental);
	            return ResponseEntity.ok().build();
	        } catch (RentalInvalidException ex) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        } catch (Exception ex) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    @DeleteMapping("/delete/{rentalId}")
	    public ResponseEntity<Object> deleteRentalById(@PathVariable String rentalId) {
	    	try {
	    		rentalServiceImpl.deleteRental(rentalId);
	    		return ResponseEntity.ok().build();
	    	}catch (RentalInvalidException ex) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        } catch (Exception ex) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

}
	    
	