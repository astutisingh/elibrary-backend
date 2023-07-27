package com.rental.service;

import java.io.InvalidClassException;
import java.util.List;
import java.util.Optional;

import com.rental.exception.RentalInvalidException;
import com.rental.model.Rental;

public interface IRentalService {
	
	public Rental addRental(String bookId,Rental rentalObj) throws RentalInvalidException, InvalidClassException;
	public Rental getRentalById(String rentalId) throws RentalInvalidException;
	public Rental updateRental(String rentalId, Rental rental) throws RentalInvalidException;
	public List<Rental> viewAll();



}
