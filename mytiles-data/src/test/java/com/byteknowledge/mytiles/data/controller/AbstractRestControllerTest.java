package com.byteknowledge.mytiles.data.controller;

import java.util.UUID;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.byteknowledge.mytiles.dao.UserDao;
import com.byteknowledge.mytiles.data.MyTilesDataApplication;
import com.byteknowledge.mytiles.data.MyTilesMockDataApplication;
import com.byteknowledge.mytiles.model.User;
import com.byteknowledge.mytiles.service.Service;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MyTilesDataApplication.class, MyTilesMockDataApplication.class}) 
@WebAppConfiguration
@IntegrationTest("server.port:0")
public abstract class AbstractRestControllerTest {

	@Autowired
    private WebApplicationContext context;
	
	// Use this to get random port chosen for Integration Tests server.port:0
    @Value("${local.server.port}")
    protected int port;
	
	@Autowired
	private UserDao userDao;
	
    protected User userAndrew;
    protected User userFrank;
	
	@BeforeClass
	public static void setUpOnce() {

	}
	
	
	@Before
	public void setUp() {
		RestAssuredMockMvc.mockMvc(MockMvcBuilders.webAppContextSetup(context).build());
		RestAssured.port = port;
		
		clearAllUsers();
		
        userAndrew = new User();
        userAndrew.setId(UUID.fromString(Service.USER2_ID));
        userAndrew.setUserName("awerhane");
        userAndrew.setFirstName("Andrew");
        userAndrew.setLastName("Werhane");
        userDao.save(userAndrew);
		
        userFrank = new User();
        userFrank.setId(UUID.fromString(Service.USER1_ID));
        userFrank.setUserName("fcarta");
        userFrank.setFirstName("Frank");
        userFrank.setLastName("Carta");
        userDao.save(userFrank);
	}
	
	private void clearAllUsers() {
		for (final User user : userDao.list()) {
			userDao.remove(user);
		}
	}
}
