package com.byteknowledge.mytiles.data.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.byteknowledge.mytiles.data.EmbeddedRedis;
import com.byteknowledge.mytiles.data.MyTilesMockRedisConfig;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("com.byteknowledge.mytiles")
@SpringApplicationConfiguration(classes = {MyTilesMockRedisConfig.class}) 
@WebAppConfiguration
@IntegrationTest("server.port:0")
@PropertySource("classpath:/test-redis.properties")
public abstract class AbstractRestControllerTest {

	@Autowired
    private WebApplicationContext context;

	// Use this to get random port chosen for Integration Tests server.port:0
    @Value("${local.server.port}")
    protected static int port;
    
    // on first instantiation of this bean the embedded redis server will start and it will stop when the tests are over
    private static EmbeddedRedis embeddedRedis;
    static {
    	if (embeddedRedis == null) {
    		embeddedRedis = new EmbeddedRedis();
    	}
    }
    
	@Before
	public void setUp() throws Exception {
		RestAssuredMockMvc.mockMvc(MockMvcBuilders.webAppContextSetup(context).build());
		RestAssured.port = port;
		
		clearData();
		
		setUpData();
	}
	
	protected void setUpData() {
		
	}
	
	protected void clearData() {
		
	}
}
