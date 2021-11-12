package com.company.Summative2GoudeauCody.dao;

import com.company.Summative2GoudeauCody.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookInventoryDaoJdbcTemplateImpl implements BookInventoryDao {

    //Prepared Statements
    private static final String INSERT_BOOK_SQL =
            "insert into book (isbn, publish_date, fk_book_author, title, fk_book_publisher, price) values (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_BOOK_SQL =
            "select * from book where book_id = ?";

    private static final String SELECT_ALL_BOOK_SQL =
            "select * from book";

    private static final String DELETE_BOOK_SQL =
            "delete from book where book_id = ?";

    private static final String UPDATE_BOOK_SQL =
            "update book set isbn = ?, publish_date = ?, fk_book_author = ?, title = ?, fk_book_publisher = ?, price = ? where book_id = ?";


    //jdbcTemplate
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookInventoryDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}


    @Override
    public Book getBook(int id) {

        try
        {
            return jdbcTemplate.queryForObject(SELECT_BOOK_SQL, this::mapRowToBook, id);
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }

    }

    @Override
    public List<Book> getAllBooks() {

        return jdbcTemplate.query(SELECT_ALL_BOOK_SQL, this::mapRowToBook);
    }

    @Override
    @Transactional
    public Book addBook(Book book) {

        jdbcTemplate.update(INSERT_BOOK_SQL,
                book.getIsbn(),
                book.getPublish_date(),
                book.getFk_book_author(),
                book.getTitle(),
                book.getFk_book_publisher(),
                book.getPrice());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        book.setId(id);

        return book;
    }

    @Override
    public void updateBook(Book book) {

        jdbcTemplate.update(UPDATE_BOOK_SQL,
                book.getIsbn(),
                book.getPublish_date(),
                book.getFk_book_author(),
                book.getTitle(),
                book.getFk_book_publisher(),
                book.getPrice(),
                book.getId());
    }

    @Override
    public void deleteBook(int id) {

        jdbcTemplate.update(DELETE_BOOK_SQL,id);

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

    private Book mapRowToBook(ResultSet rs, int rowNum) throws SQLException {

        Book book = new Book();
        book.setId(rs.getInt("book_id"));
        book.setIsbn(rs.getString("isbn"));
        book.setPublish_date(rs.getDate("publish_date").toLocalDate());
        book.setFk_book_author(rs.getInt("fk_book_author"));
        book.setTitle(rs.getString("title"));
        book.setFk_book_publisher(rs.getInt("fk_book_publisher"));
        book.setPrice(rs.getBigDecimal("price"));

        return book;
    }

}
