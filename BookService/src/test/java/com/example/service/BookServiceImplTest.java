package com.example.service;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.exception.BookInvalidException;
import com.example.model.Book;

@SpringBootTest
public class BookServiceImplTest {
	
	
	@Autowired
	BookServiceImpl bookServiceImpl;


    @Test
    void viewByBookId() throws BookInvalidException {
        Book b = bookServiceImpl.viewByBookId("103");
        String bookName=b.getBookId();
        assertFalse(bookName.isEmpty());
    }


    @Test
    void viewbybookName() throws BookInvalidException{
        List<Book> b = bookServiceImpl.viewbybookName("Let us C");
        String bookName=b.toString();
        assertFalse(bookName.isEmpty());

    }


    @Test
    void viewAll() throws BookInvalidException{
        List<Book> b = bookServiceImpl.viewAll();
        System.out.println(b);
        assertFalse(b.isEmpty());

    }

    @Test
    void findById() throws BookInvalidException{
        Book s = bookServiceImpl.findById("103");
        String bookName=s.getBookName();
        assertFalse(bookName.isEmpty());


    }

    @Test
    void getBookById() throws BookInvalidException{
        Book s = bookServiceImpl.getBookById("104");


    }

    @Test
    void getBookByType() throws BookInvalidException{
        List<Book> s = bookServiceImpl.getBookByType("Programming");
        assertFalse(s.isEmpty());
}
	
	

}
