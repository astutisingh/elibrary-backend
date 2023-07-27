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

import com.example.exception.BookInvalidException;
import com.example.model.Book;
import com.example.service.BookServiceImpl;

@RestController
@RequestMapping("/book")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class BookController {

    @Autowired
	private BookServiceImpl bookServiceImpl;


	@PostMapping("/saveBook")
	public ResponseEntity<Book> addBook(@RequestBody Book cObj) {
		try {
	        // addBook method to save the book
			Book savedBook = bookServiceImpl.addBook(cObj);
	        // Returning a ResponseEntity with the saved book and HTTP status(OK)
			return ResponseEntity.ok(savedBook);
		} catch (Exception e) {
	        // Returning a ResponseEntity with HTTP status(Internal Server Error)
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@GetMapping("/viewBookByBookId/{bookId}")
	public ResponseEntity<Book> viewByBookId(@PathVariable String bookId) {

		try {
	        // viewByBookId method to retrieve the book
			Book book = bookServiceImpl.viewByBookId(bookId);
			if (book != null) {
				return new ResponseEntity<>(book, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/book/{bookName}")
	public ResponseEntity<List<Book>> viewByBookName(@PathVariable String bookName) throws BookInvalidException {
	     // Retrieve the book by its bookName
			List<Book> books = bookServiceImpl.viewbybookName(bookName);
			if (books != null && !books.isEmpty()) {
				return new ResponseEntity<>(books, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	
	}

	@GetMapping("/viewAll")
	public ResponseEntity<List<Book>> viewAll() {
		try {
	       //viewAll() method of the bookServiceImpl to retrieve a list of books
			List<Book> books = bookServiceImpl.viewAll();
	        // Check if the list of books is not empty
			if (books.size() > 0) {
	            // If books are there, return them with an HTTP status(OK)
				return new ResponseEntity<>(books, HttpStatus.OK);
			} else {
	            // If there are no books, return an HTTP status(NOT_FOUND)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
	        // If an exception occurs during the process, return an HTTP status(INTERNAL_SERVER_ERROR)
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/addRating/{bookId}/{rating}")
	public ResponseEntity<Book> addRatingFromRental(@PathVariable String bookId, @PathVariable float rating)
	{
		try {
			Book addRating = bookServiceImpl.addRatingFromRental(bookId, rating);
			return new ResponseEntity<>(addRating, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	@PutMapping("/update/{bookId}")
	public ResponseEntity<Book> updateBook(@PathVariable String bookId, @RequestBody Book book) {
		try {
			Book update = bookServiceImpl.updateBook(bookId, book);
			return new ResponseEntity<>(update, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/view/{bookId}")
	public ResponseEntity<Book> getBookListFromBookId(@PathVariable String bookId)
	{
		try {
			Book book = bookServiceImpl.getBookById(bookId);
			return new ResponseEntity<>(book,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/viewByType/{bookType}")
	public ResponseEntity<List<Book>> getBookListFromBookType(@PathVariable String bookType)
	{
		try {
			List<Book> book = bookServiceImpl.getBookByType(bookType);
			return new ResponseEntity<List<Book>>(book,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping("/delete/{bookId}")
	 public ResponseEntity<Book> deleteBookById(@PathVariable String bookId) {
		try {
			Book book = bookServiceImpl.deleteBook(bookId);
			return ResponseEntity.ok().build();
    	}catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


           
}

