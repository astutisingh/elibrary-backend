package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.BookInvalidException;
import com.example.model.Book;

import com.example.repository.BookRepository;
import com.netflix.discovery.converters.Auto;



@Service
public class BookServiceImpl implements IBookService{
	
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BookServiceImpl.class);

	
	@Autowired
	BookRepository bookRepository;

	
	
	@Autowired
	private SequenceGeneratorService generatorService;


	@Override
	public Book addBook(Book book){
	    // Generate a unique book ID using the generatorService and set it on the book object
		book.setBookId(String.valueOf(generatorService.generateSequence(Book.SEQUENCE_NAME)));
	    // Log the generated book ID
		log.info("Book ID "+book.getBookId());
	    // Save the book object to the book repository and return the saved book
		return bookRepository.save(book);

}

	
	@Override
	public Book viewByBookId(String bookId) throws BookInvalidException {
	    // Check if the bookId is null or empty
		if (bookId == null || bookId.isEmpty()) {
	        throw new BookInvalidException("Invalid bookId");
		}
	    // findById method to retrieve the book based on the bookId
	    Book book = findById(bookId);
	    // Check if the book is found
	    if (book != null) {
	        // Set the bookId on the book object (assuming it's not already set)
//	        book.setBookId(bookId);
	        return book;
	    }else {
	        // throw a BookInvalidException with the message "Book not found"
	    throw new BookInvalidException("Book not found");
	    }

}
	
	@Override
	public List<Book> viewbybookName(String BookName) throws BookInvalidException {
	    // Check if the BookName is null or empty
		if (BookName == null || BookName.isEmpty()) {
	        throw new BookInvalidException("Invalid bookName");
	    }
	    // Retrieve the book from the book repository based on the BookName
		List<Book> books = bookRepository.findByBookName(BookName);
	    // Check if the book is found
		if(books==null || books.isEmpty())
			throw new BookInvalidException("Book not found");
		else{
			return books;
		}

	}
	
	@Override
	public Book updateBook(String bookId, Book book) throws BookInvalidException {
	    // Retrieve the book from the book repository based on the bookId
		Book bookObj = bookRepository.findByBookId(bookId);
	    // Check if the book is found
		if(bookObj!=null)
		{
	        // Update the book properties with the values from the provided book object
			bookObj.setBookName(book.getBookName());
			bookObj.setBookType(book.getBookType());
			bookObj.setRating(book.getRating());
			bookObj.setStock(book.getStock());
			bookObj.setPrice(book.getPrice());
	        
			// Save the updated book object back to the repository
			bookObj = bookRepository.save(bookObj);
		}
		else
		{
	        // throw a BookInvalidException with the message "Error Occurred while updating!"
			throw new BookInvalidException("Error Occured while updating!");
		}
	    // return the updated book object
		return bookObj;
	}


    @Override
    public List<Book> viewAll() {
        return bookRepository.findAll();

    }
    
	public Book findById(String bookId) {
		// TODO Auto-generated method stub
		List<Book> book=bookRepository.findAll();
		for(Book b:book) {
			if(b.getBookId().equals(bookId)) {
				return b;
			}
		}
		
		return null;
	}
	
	@Override
	public Book getBookById(String bookId)
	{
		return bookRepository.getByBookId(bookId);
	}
	
	public List<Book> getBookByType(String bookType)
	{
		return bookRepository.getByBookType(bookType);
	}
	
	@Override
	public Book addRatingFromRental(String bookId, float rating) throws BookInvalidException
	{
		Book getBook = bookRepository.findByBookId(bookId);
		if(getBook.equals(null))
		{
			 throw new BookInvalidException("Invalid bookId: " + bookId);
			
		}
		getBook.setRating(rating);
		return bookRepository.save(getBook);
	}


	public Book deleteBook(String bookId) {
		// TODO Auto-generated method stub
		 Book bookToDelete = bookRepository.findById(bookId).orElseThrow();
	        bookRepository.delete(bookToDelete);
	        return bookToDelete;
	}




}
	