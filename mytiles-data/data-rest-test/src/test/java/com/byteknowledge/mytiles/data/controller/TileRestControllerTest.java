package com.byteknowledge.mytiles.data.controller;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;

import java.util.Calendar;
import java.util.UUID;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.byteknowledge.mytiles.dao.TileDao;
import com.byteknowledge.mytiles.dao.UserDao;
import com.byteknowledge.mytiles.model.Tile;
import com.byteknowledge.mytiles.model.User;
import com.byteknowledge.mytiles.service.Service;

public class TileRestControllerTest extends AbstractRestControllerTest {

	@Autowired
	protected UserDao userDao;

    protected User userAndrew;
    protected User userFrank;
	
	@Autowired
	protected TileDao tileDao;
	
	protected Tile tileHalarious;
	protected Tile tileFunny;
	
	@Override
	protected void setUpData() {
		
        userFrank = new User();
        userFrank.setId(UUID.fromString(Service.USER1_ID));
        userFrank.setUserName("fcarta");
        userFrank.setFirstName("Frank");
        userFrank.setLastName("Carta");
        userDao.save(userFrank);

        userAndrew = new User();
        userAndrew.setId(UUID.fromString(Service.USER2_ID));
        userAndrew.setUserName("awerhane");
        userAndrew.setFirstName("Andrew");
        userAndrew.setLastName("Werhane");
        userDao.save(userAndrew);		
		
        tileHalarious = new Tile();
        tileHalarious.setId(UUID.fromString(Service.TILE1_ID));
        tileHalarious.setCreatorId(userFrank.getId());
        tileHalarious.setCreatedTime(Calendar.getInstance().getTimeInMillis());
        tileHalarious.setLabel("Halarious");
        tileDao.save(tileHalarious);
        
        tileFunny = new Tile();
        tileFunny.setId(UUID.fromString(Service.TILE2_ID));
        tileFunny.setCreatorId(userAndrew.getId());
        tileFunny.setCreatedTime(Calendar.getInstance().getTimeInMillis());
        tileFunny.setLabel("Funny");    
        tileDao.save(tileFunny);
	}
	
	@Override
	protected void clearData() {
		for (final Tile tile : tileDao.list()) {
			tileDao.remove(tile);
		}
		
		for (final User user : userDao.list()) {
			userDao.remove(user);
		}
	}	

	@Test
	public void getTileHalarious() {
		given()
			.when()
				.get("/tiles/{id}", Service.TILE1_ID)
					.then()
					.statusCode(HttpStatus.SC_OK)
					.contentType("application/json")
					.body("id", Matchers.is(tileHalarious.getId().toString()))
					.body("creatorId", Matchers.is(tileHalarious.getCreatorId().toString()))
					.body("createdTime", Matchers.is(tileHalarious.getCreatedTime()))
					.body("label", Matchers.is(tileHalarious.getLabel()))
					.log().all();
	}
	
	@Test
	public void getTiles() {
		given()
			.when()
				.get("/tiles")
					.then()
					.statusCode(HttpStatus.SC_OK)
					.contentType("application/json")
					.body("size()", Matchers.equalTo(2))
					.log().all();		
	}
	
	
	@Test
	public void putTile() {

		final Tile putTile = new Tile();
		putTile.setId(UUID.fromString("9103f50b-5699-4c2e-b54a-989371567263"));
		putTile.setCreatorId(userFrank.getId());
		putTile.setCreatedTime(Calendar.getInstance().getTimeInMillis());
		putTile.setLabel("Put Tile");
		
		given()
			.body(putTile)
			.contentType("application/json")
			.when()
				.put("/tiles")
					.then()
					.statusCode(HttpStatus.SC_OK)
					.log().all();
		
		given()
			.when()
				.get("/tiles")
					.then()
					.statusCode(HttpStatus.SC_OK)
					.contentType("application/json")
					.body("size()", Matchers.equalTo(3))
					.log().all();
	}
	
	@Test
	public void postTile() {
		
		final String newLabel = "cool"; 
		tileHalarious.setLabel(newLabel);
		
		given()
			.body(tileHalarious)
			.contentType("application/json")
			.when()
				.post("/tiles/{id}", Service.TILE1_ID)
					.then()
					.statusCode(HttpStatus.SC_OK)
					.log().all();
		
		given()
			.when()
				.get("/tiles")
					.then()
					.statusCode(HttpStatus.SC_OK)
					.contentType("application/json")
					.body("size()", Matchers.equalTo(2))
					.log().all();
		
		given()
			.when()
				.get("/tiles/{id}", Service.TILE1_ID)
					.then()
					.statusCode(HttpStatus.SC_OK)
					.contentType("application/json")
					.body("id", Matchers.is(tileHalarious.getId().toString()))
					.body("creatorId", Matchers.is(tileHalarious.getCreatorId().toString()))
					.body("createdTime", Matchers.is(tileHalarious.getCreatedTime()))
					.body("label", Matchers.is(newLabel))
					.log().all();
	}	
	
	@Test
	public void deleteTile() {
		
		given()
			.body(tileHalarious)
			.contentType("application/json")
			.when()
				.post("/tiles/{id}", Service.TILE1_ID)
					.then()
					.statusCode(HttpStatus.SC_OK)
					.log().all();
		
		given()
			.when()
				.delete("/tiles/{id}", Service.TILE1_ID)
					.then()
					.statusCode(HttpStatus.SC_OK)
					.log().all();
		
		given()
			.when()
				.get("/tiles")
					.then()
					.statusCode(HttpStatus.SC_OK)
					.contentType("application/json")
					.body("size()", Matchers.equalTo(1))
					.log().all();
		
		given()
			.body(tileFunny)
			.contentType("application/json")
			.when()
				.post("/tiles/{id}", Service.TILE2_ID)
					.then()
					.statusCode(HttpStatus.SC_OK)
					.log().all();
	}
	
}
