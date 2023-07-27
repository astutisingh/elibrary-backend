package com.example.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
	
	Book getRatingByBookId(String BookId);

	List<Book> findByBookName(String bookName);
	
	Book getByBookId(String bookId);
	
	List<Book> getByBookType(String bookType);
	
	Book findByBookId(String bookId);
}
