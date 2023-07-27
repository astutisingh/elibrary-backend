package com.example.service;

import java.util.List;

import com.example.exception.BookInvalidException;
import com.example.model.Book;

public interface IBookService {
	
	public Book updateBook(String BookId, Book book) throws BookInvalidException;
	public Book viewByBookId(String BookId) throws BookInvalidException;
	public List<Book> viewbybookName (String BookName) throws BookInvalidException;
	public List<Book> viewAll();
	public Book getBookById(String bookId);
	public Book addBook(Book cObj) throws BookInvalidException, Exception;
	public Book addRatingFromRental(String bookId, float rating) throws BookInvalidException;

}

