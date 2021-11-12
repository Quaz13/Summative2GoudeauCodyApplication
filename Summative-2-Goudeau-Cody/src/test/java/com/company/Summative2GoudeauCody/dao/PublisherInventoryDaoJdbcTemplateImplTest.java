package com.company.Summative2GoudeauCody.dao;

import com.company.Summative2GoudeauCody.dto.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PublisherInventoryDaoJdbcTemplateImplTest {

    @Autowired
    protected PublisherInventoryDao dao;

    @Before
    public void setUp() throws Exception {
        List<Publisher> publisherList = dao.getAllPublishers();

        for(Publisher publisher: publisherList){
            dao.deletePublisher(publisher.getPublisher_id());
        }
    }

    /*
    private String name;
    private String street;
    private String city;
    private String state;
    private String postal_code;
    private String phone;
    private String email;
     */

    @Test
    public void addGetDeletePublisher() {
        //Arrange
        Publisher publisher = new Publisher();
        publisher.setName("Cody");
        publisher.setStreet("brooklyn ave");
        publisher.setCity("Los Angeles");
        publisher.setState("CA");
        publisher.setPostal_code("93754");
        publisher.setPhone("835-846-2794");
        publisher.setEmail("c@gmail.com");

        //Act
        publisher = dao.addPublisher(publisher);
        Publisher publisher1 = dao.getPublisher(publisher.getPublisher_id());

        //Assert
        assertEquals(publisher, publisher1);

        //Act
        dao.deletePublisher(publisher.getPublisher_id());
        publisher1 = dao.getPublisher(publisher.getPublisher_id());

        //Assert
        assertNull(publisher1);
    }

    @Test
    public void updatePublisher() {
        //Arrange
        Publisher publisher = new Publisher();
        publisher.setName("Cody");
        publisher.setStreet("brooklyn ave");
        publisher.setCity("Los Angeles");
        publisher.setState("CA");
        publisher.setPostal_code("93754");
        publisher.setPhone("835-846-2794");
        publisher.setEmail("c@gmail.com");

        //Add new Publisher
        publisher = dao.addPublisher(publisher);

        //Set the new publisher postal_code
        publisher.setPostal_code("95735");

        //update the new publisher postal_code
        dao.updatePublisher(publisher);

        //Save the new publisher postal_code
        Publisher publisher1 = dao.getPublisher(publisher.getPublisher_id());

        //Make sure the new publisher postal_code is saved
        assertEquals(publisher, publisher1);
    }

    @Test
    public void getAllPublishers() {
        //Arrange
        Publisher publisher = new Publisher();
        publisher.setName("Cody");
        publisher.setStreet("brooklyn ave");
        publisher.setCity("Los Angeles");
        publisher.setState("CA");
        publisher.setPostal_code("93754");
        publisher.setPhone("835-846-2794");
        publisher.setEmail("c@gmail.com");

        dao.addPublisher(publisher);

        //Arrange
        publisher = new Publisher();
        publisher.setName("bob");
        publisher.setStreet("lit street");
        publisher.setCity("London");
        publisher.setState("UK");
        publisher.setPostal_code("12345");
        publisher.setPhone("846-768-3333");
        publisher.setEmail("bob@gmail.com");

        dao.addPublisher(publisher);
        List<Publisher> publisherList = dao.getAllPublishers();

        //Assert
        assertEquals(2, publisherList.size());

    }
}




