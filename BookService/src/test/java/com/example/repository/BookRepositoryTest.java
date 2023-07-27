package com.example.repository;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.model.Book;

@SpringBootTest
public class BookRepositoryTest {
	
	
	@Autowired
    BookRepository bookRepository;


    @Test
    void findByBookName() {
        List<Book> s = bookRepository.findByBookName("Let us C");
        assertFalse(s.isEmpty());
    }

    @Test
    void findByBookId() {
        Book s = bookRepository.findByBookId("103");
        String bookName=s.getBookName();
        assertFalse(bookName.isEmpty());

    }

    @Test
    void getRatingByBookId() {
        Book s = bookRepository.getRatingByBookId("104");
        float rating= s.getRating();
        assertFalse(Float.isNaN(rating));
    }

    @Test
    void getByBookId() {
        Book s = bookRepository.getByBookId("104");
        assertFalse(((List<Book>) s).isEmpty());
    }
    @Test
    void getByBookType() {
        List<Book> s = bookRepository.getByBookType("Programming");
        assertFalse(s.isEmpty());
    }
	
	
	

}
