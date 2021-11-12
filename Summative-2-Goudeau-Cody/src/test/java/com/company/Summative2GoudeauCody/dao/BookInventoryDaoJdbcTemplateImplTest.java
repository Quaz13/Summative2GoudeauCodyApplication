package com.company.Summative2GoudeauCody.dao;

import com.company.Summative2GoudeauCody.dto.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookInventoryDaoJdbcTemplateImplTest {

    @Autowired
    protected BookInventoryDao dao;

    @Before
    public void Setup() throws Exception {
        List<Book> mList = dao.getAllBooks();

        for(Book book: mList){
            dao.deleteBook(book.getId());
        }
    }

       /*
        private int id;
    private String isbn;
    private String publish_date;
    private int author_id;
    private String title;
    private int publisher_id;
    private BigDecimal price;
     */

    @Test
    public void addGetDeleteBook() {
        //Arrange
        Book book = new Book();
        book.setIsbn("9780545010221");
        book.setPublish_date(LocalDate.of(2020,02,21));
        book.setFk_book_author(book.getFk_book_author());
        book.setTitle("The New Capitol");
        book.setFk_book_publisher(book.getFk_book_publisher());
        book.setPrice(new BigDecimal("17.45"));

        //Act
        book = dao.addBook(book);
        Book book1 = dao.getBook(book.getId());

        //Assert
        assertEquals(book, book1);

        //Act
        dao.deleteBook(book.getId());
        book1 = dao.getBook(book.getId());

        //Assert
        assertNull(book1);
    }

    @Test
    public void updateBook() {
        //Arrange
        Book book = new Book();
        book.setIsbn("9780545010221");
        book.setPublish_date(LocalDate.of(2020,02,21));
        book.setFk_book_author(book.getFk_book_author());
        book.setTitle("The New Capitol");
        book.setFk_book_publisher(book.getFk_book_publisher());
        book.setPrice(new BigDecimal("17.45"));

        //Add new book
        book = dao.addBook(book);

        //Set the new book title
        book.setTitle("The Old Town");

        //update the new book title
        dao.updateBook(book);

        //Save the new book title
        Book book1 = dao.getBook(book.getId());

        //Make sure the new book title is saved
        assertEquals(book, book1);


    }

    @Test
    public void getAllBooks() {
        //Arrange
        Book book = new Book();
        book.setIsbn("9780545010221");
        book.setPublish_date(LocalDate.of(2020,02,21));
        book.setFk_book_author(book.getFk_book_author());
        book.setTitle("The New Capitol");
        book.setFk_book_publisher(book.getFk_book_publisher());
        book.setPrice(new BigDecimal("17.45"));

        dao.addBook(book);

        //Arrange
        book = new Book();
        book.setIsbn("8694756384659");
        book.setPublish_date(LocalDate.of(2019, 05, 04));
        book.setFk_book_author(book.getFk_book_author());
        book.setTitle("The Grey Wolf");
        book.setFk_book_publisher(98);
        book.setPrice(new BigDecimal("56.89"));

        dao.addBook(book);
        List<Book> bookList = dao.getAllBooks();

        //Assert
        assertEquals(2, bookList.size());
    }

/*
    @Test
    public void getBookByAuthor() {
        //Arrange
        Book book = new Book();
        book.setIsbn("9780545010221");
        book.setPublish_date(LocalDate.of(2020,02,21));
        book.setFk_book_author(book.getFk_book_author());
        book.setTitle("The New Capitol");
        book.setFk_book_publisher(book.getFk_book_publisher());
        book.setPrice(new BigDecimal("17.45"));

        dao.addBook(book);

        //Arrange
        book = new Book();
        book.setIsbn("8694756384659");
        book.setPublish_date(LocalDate.of(2019, 05, 04));
        book.setFk_book_author(book.getFk_book_author());
        book.setTitle("The Grey Wolf");
        book.setFk_book_publisher(book.getFk_book_publisher());
        book.setPrice(new BigDecimal("56.89"));

        dao.addBook(book);

        //Arrange
        book = new Book();
        book.setIsbn("8694756384659");
        book.setPublish_date(LocalDate.of(2019, 05, 04));
        book.setFk_book_author(book.getFk_book_author());
        book.setTitle("The Grey Wolf Den");
        book.setFk_book_publisher(book.getFk_book_publisher());
        book.setPrice(new BigDecimal("56.89"));

        dao.addBook(book);
        List<Book> bookList = dao.getBookByAuthor(book.getFk_book_author());

        //Assert
        assertEquals(2, bookList.size());

        //Act
        bookList = dao.getBookByAuthor(book.getFk_book_author());

        //Assert
        assertEquals(1,bookList.size());

        //Act
        bookList = dao.getBookByAuthor(book.getFk_book_author());

        //Assert
        assertEquals(0, bookList.size());
*/




}