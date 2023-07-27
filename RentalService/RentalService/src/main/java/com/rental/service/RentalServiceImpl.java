package com.rental.service;

import java.io.InvalidClassException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.rental.constants.RentalConstants;
import com.rental.exception.RentalInvalidException;
import com.rental.model.Book;
import com.rental.model.Rental;
import com.rental.repository.RentalRepository;

@Service
public class RentalServiceImpl implements IRentalService{
	
	private static final Logger LOGGER=LoggerFactory.getLogger(RentalServiceImpl.class);


	@Autowired
	private RentalRepository rentalRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	private RestTemplate restTemplate;
	

	
	@Override
	public Rental addRental(String bookId,Rental rentalObj) throws RentalInvalidException, InvalidClassException {
		// TODO Auto-generated method stub 
		Rental obj = new Rental();
		 try {
			Book book = restTemplate.getForObject("http://localhost:8081/book/view/"+bookId, Book.class);
			 LOGGER.info("List of Book "+book.toString());
			 if(book!=null)
			 {
				 obj.setRentalId(String.valueOf(sequenceGeneratorService.generateSequence(Rental.SEQUENCE_NAME)));
				 obj.setUsername(rentalObj.getUsername());
				 obj.setBooks(book);
				 obj.setStartDate(rentalObj.getStartDate());
				 obj.setEndDate(rentalObj.getEndDate());
				 obj.setStatus(rentalObj.getStatus());
				 obj.setBookRated("No");
			 }
			
	        } catch (Exception e) {
	            throw new InvalidClassException("Error creating rental: " + e.getMessage());
	        }
		 return rentalRepository.save(obj);
	    }
	
   
    
    @Override
	public Rental getRentalById(String rentalId)throws RentalInvalidException{
		// TODO Auto-generated method stub
    	if (rentalId == null || rentalId.isEmpty()) {
	        throw new RentalInvalidException("Invalid rentalId");
		}
	    Rental rental = findById(rentalId);
	   
	    if (rental != null) {

	        return rental;
	    }else {
	        
	    throw new RentalInvalidException("Rental not found");
	    }
	}
    
    public List<Rental> getByUsername(String username) throws RentalInvalidException {
    	List<Rental> rentals = rentalRepository.getByUsername(username);
    	return rentals;
    }

    @Override
	public Rental updateRental(String rentalId, Rental rental) throws RentalInvalidException {
		// TODO Auto-generated method stub
	    Optional<Rental> optionalExistingRental = rentalRepository.findByRentalId(rentalId);

	    if (optionalExistingRental.isPresent()) {
	        Rental existingRental = optionalExistingRental.get();
	        
//	        if (rental.getStartDate().isAfter(rental.getEndDate())) {
//	            throw new RentalInvalidException("Invalid rental dates.");
//	        }
//	        
	        existingRental.setStatus(rental.getStatus());
	        existingRental.setStartDate(rental.getStartDate());
	        existingRental.setEndDate(rental.getEndDate());
	        return rentalRepository.save(existingRental);
	    } else {
	        throw new RentalInvalidException("Rental not found with ID: " + rentalId);
	    }
	}

	@Override
	public List<Rental> viewAll(){
		// TODO Auto-generated method stub
	    return rentalRepository.findAll();

	}
	
	public Rental findById(String rentalId) {
		// TODO Auto-generated method stub
		List<Rental> rental=rentalRepository.findAll();
		for(Rental b:rental) {
			if(b.getRentalId().equals(rentalId)) {
				return b;
			}
		}
		
		return null;
	}

	public Rental addRatingForBooks(String rentalId, float rating, Rental rental1) throws RentalInvalidException
	{
		String bookId = "";
		if(!rentalRepository.findByRentalId(rentalId).isPresent())
		{
			throw new RentalInvalidException("RentalId doesn't exists.");
		}
		//retrieves the rental object
		Rental rental = rentalRepository.getBookIdByRentalId(rentalId);
		Book bookList = rental.getBooks();
		
	    bookId = bookList.getBookId();
	    System.out.println("bookId :"+bookId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Book> httpEntityBook = new HttpEntity<>(headers);
		LOGGER.info("BookID "+bookId);
		
		
		Book book = restTemplate.getForObject("http://localhost:8081/book/viewBookByBookId/"+bookId, Book.class);
		if(bookList.getBookId().equals(book.getBookId()))
		{
			bookList.setRating(rating);
		}
		LOGGER.info("BookID from restTemplate "+book.getBookId());
		
		ResponseEntity<Book> responseBook =  restTemplate.exchange(RentalConstants.ADD_RATING+book.getBookId()+"/"+rating,HttpMethod.PUT, httpEntityBook,Book.class);
		
		rental.setBookRated(rental1.getBookRated());
	
		
		
		
		HttpStatusCode statusCode = responseBook.getStatusCode();
		
		LOGGER.info("Status Code for Update Course Ratings "+statusCode);
		LOGGER.info("Response Body "+responseBook.getBody().getRating());
		return rentalRepository.save(rental);
		
		
	}
	
	public void deleteRental(String rentalId) throws Exception{
		rentalRepository.deleteByRentalId(rentalId);
	}
	


}
