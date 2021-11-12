package com.company.Summative2GoudeauCody.dao;

import com.company.Summative2GoudeauCody.dto.Author;
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
public class AuthorInventoryDaoJdbcTemplateImplTest {

    @Autowired
    protected AuthorInventoryDao dao;

    @Before
    public void Setup() throws Exception {
        List<Author> mList = dao.getAllAuthors();

        for(Author author: mList){
            dao.deleteAuthor(author.getId());
        }

    }

    /*
        private int id;
    private String first_name;
    private String last_name;
    private String street;
    private String city;
    private String state;
    private String postal_code;
    private String phone;
    private String email;
     */

    @Test
    public void addGetDeleteAuthor() {
        Author author = new Author();
        author.setFirst_name("Cody");
        author.setLast_name("Goudeau");
        author.setStreet("Grand Central Parkway");
        author.setCity("Forest Hills");
        author.setState("NY");
        author.setPostal_code("11375");
        author.setPhone("736-957-9836");
        author.setEmail("cj@gmail.com");

        //Act
        author = dao.addAuthor(author);
        Author author1 = dao.getAuthor(author.getId());

        //Assert
        assertEquals(author, author1);

        //Assert
        dao.deleteAuthor(author.getId());
        author1 = dao.getAuthor(author.getId());

        //Assert
        assertNull(author1);
    }

    @Test
    public void updateAuthor() {
        //Arrange
        Author author = new Author();
        author.setFirst_name("Cody");
        author.setLast_name("Goudeau");
        author.setStreet("Grand Central Parkway");
        author.setCity("Forest Hills");
        author.setState("NY");
        author.setPostal_code("11375");
        author.setPhone("736-957-9836");
        author.setEmail("cj@gmail.com");


        //Add new author
        author = dao.addAuthor(author);

        //Set the new author name
        author.setFirst_name("Bill");

        //update the new author name
        dao.updateAuthor(author);

        //Save the new author name
        Author author1 = dao.getAuthor(author.getId());

        //Make sure the new author name is saved
        assertEquals(author, author1);

    }

    @Test
    public void getAllAuthors () {
        //Arrange
        Author author = new Author();
        author.setFirst_name("Cody");
        author.setLast_name("Goudeau");
        author.setStreet("Grand Central Parkway");
        author.setCity("Forest Hills");
        author.setState("NY");
        author.setPostal_code("11375");
        author.setPhone("736-957-9836");
        author.setEmail("cj@gmail.com");

        dao.addAuthor(author);

        //Arrange
        author = new Author();
        author.setFirst_name("Sam");
        author.setLast_name("Fisher");
        author.setStreet("Space Cadet Drive");
        author.setCity("Chicago");
        author.setState("IL");
        author.setPostal_code("47832");
        author.setPhone("892-751-9002");
        author.setEmail("sf@gmail.com");

        dao.addAuthor(author);
        List<Author> authorList = dao.getAllAuthors();

        //Assert
        assertEquals(2, authorList.size());

    }

}