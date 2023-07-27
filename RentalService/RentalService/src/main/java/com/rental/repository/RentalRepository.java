package com.rental.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.rental.model.Rental;

@Repository
public interface RentalRepository extends MongoRepository<Rental, String> {

//	Optional<Rental> updateRental(String rentalId);
	
	Optional<Rental> findByRentalId(String rentalId);
	
	List<Rental> findAll();
	
	Rental getBookIdByRentalId(String rentalId);
	
	Boolean existsByRentalId(String rentalId);
	
	List<Rental> getByUsername(String username);
	
	Rental deleteByRentalId(String rentalId);

}
