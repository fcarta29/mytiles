package com.byteknowledge.mytiles.data.controller;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;

import java.util.Calendar;
import java.util.UUID;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.byteknowledge.mytiles.dao.TileBagDao;
import com.byteknowledge.mytiles.dao.TileDao;
import com.byteknowledge.mytiles.dao.UserDao;
import com.byteknowledge.mytiles.model.Tile;
import com.byteknowledge.mytiles.model.TileBag;
import com.byteknowledge.mytiles.model.User;
import com.byteknowledge.mytiles.service.Service;

public class TileBagRestControllerTest extends AbstractRestControllerTest {

	@Autowired
	protected UserDao userDao;

    protected User userAndrew;
    protected User userFrank;
	
	@Autowired
	protected TileDao tileDao;
	
	protected Tile tileHalarious;
	protected Tile tileFunny;
	
	@Autowired
	protected TileBagDao tileBagDao;
	
	protected TileBag tileBagCool;
	protected TileBag tileBagAwesome;
	
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
        
        tileBagCool = new TileBag();
        tileBagCool.setId(UUID.fromString(Service.TILEBAG_ID));
        tileBagCool.setCreatorId(UUID.fromString(Service.USER1_ID));
        tileBagCool.setCreatedTime(Calendar.getInstance().getTimeInMillis());
        tileBagCool.setName("Bag of Cool");
        tileBagCool.addTile(tileFunny).addTile(tileHalarious);
        tileBagDao.save(tileBagCool);
        
        tileBagAwesome = new TileBag();
        tileBagAwesome.setCreatorId(UUID.fromString(Service.USER2_ID));
        tileBagAwesome.setCreatedTime(Calendar.getInstance().getTimeInMillis());
        tileBagAwesome.setName("Bag of Awesome");
        tileBagAwesome.addTile(tileFunny);
        tileBagDao.save(tileBagAwesome);
	}
	
	@Override
	protected void clearData() {
		for (final TileBag tileBag : tileBagDao.list()) {
			tileBagDao.remove(tileBag);
		}
		
		for (final Tile tile : tileDao.list()) {
			tileDao.remove(tile);
		}
		
		for (final User user : userDao.list()) {
			userDao.remove(user);
		}
	}	

	@Test
	public void getTileBagCool() {
		given()
			.when()
				.get("/tilebags/{id}", Service.TILEBAG_ID)
					.then()
					.statusCode(HttpStatus.SC_OK)
					.contentType("application/json")
					.body("id", Matchers.is(tileBagCool.getId().toString()))
					.body("creatorId", Matchers.is(tileBagCool.getCreatorId().toString()))
					.body("createdTime", Matchers.is(tileBagCool.getCreatedTime()))
					.body("name", Matchers.is(tileBagCool.getName()))
					.body("tiles.size()", Matchers.is(2))
					.log().all();
	}
	
	@Test
	public void getTileBags() {
		given()
			.when()
				.get("/tilebags")
					.then()
					.statusCode(HttpStatus.SC_OK)
					.contentType("application/json")
					.body("size()", Matchers.equalTo(2))
					.log().all();		
	}
	
	
	@Test
	public void putTileBag() {

		final TileBag putTileBag = new TileBag();
		putTileBag.setId(UUID.fromString("5673f50b-5699-4c2e-b54a-989371567263"));
		putTileBag.setCreatorId(userFrank.getId());
		putTileBag.setCreatedTime(Calendar.getInstance().getTimeInMillis());
		putTileBag.setName("Put TileBag");
		
		given()
			.body(putTileBag)
			.contentType("application/json")
			.when()
				.put("/tilebags")
					.then()
					.statusCode(HttpStatus.SC_OK)
					.log().all();
		
		given()
			.when()
				.get("/tilebags")
					.then()
					.statusCode(HttpStatus.SC_OK)
					.contentType("application/json")
					.body("size()", Matchers.equalTo(3))
					.log().all();
	}
	
	@Test
	public void postTileBag() {
				
		final Tile putTile = new Tile();
		putTile.setId(UUID.fromString("9103f50b-5699-4c2e-b54a-989371567263"));
		putTile.setCreatorId(userFrank.getId());
		putTile.setCreatedTime(Calendar.getInstance().getTimeInMillis());
		putTile.setLabel("Put Tile");
		tileDao.save(putTile);
		
		tileBagCool.addTile(putTile).setName("Bag of Coolness");
		
		given()
			.body(tileBagCool)
			.contentType("application/json")
			.when()
				.post("/tilebags/{id}", Service.TILEBAG_ID)
					.then()
					.statusCode(HttpStatus.SC_OK)
					.log().all();
		
		given()
			.when()
				.get("/tilebags")
					.then()
					.statusCode(HttpStatus.SC_OK)
					.contentType("application/json")
					.body("size()", Matchers.equalTo(2))
					.log().all();
		
		given()
			.when()
				.get("/tilebags/{id}", Service.TILEBAG_ID)
					.then()
					.statusCode(HttpStatus.SC_OK)
					.contentType("application/json")
					.body("id", Matchers.is(tileBagCool.getId().toString()))
					.body("creatorId", Matchers.is(tileBagCool.getCreatorId().toString()))
					.body("createdTime", Matchers.is(tileBagCool.getCreatedTime()))
					.body("name", Matchers.is("Bag of Coolness"))
					.body("tiles.size()", Matchers.equalTo(3))
					.log().all();
	}	
	
	@Test
	public void deleteTileBag() {
		
		given()
			.body(tileBagCool)
			.contentType("application/json")
			.when()
				.post("/tilebags/{id}", Service.TILEBAG_ID)
					.then()
					.statusCode(HttpStatus.SC_OK)
					.log().all();
		
		given()
			.when()
				.delete("/tilebags/{id}", Service.TILEBAG_ID)
					.then()
					.statusCode(HttpStatus.SC_OK)
					.log().all();
		
		given()
			.when()
				.get("/tilebags")
					.then()
					.statusCode(HttpStatus.SC_OK)
					.contentType("application/json")
					.body("size()", Matchers.equalTo(1))
					.log().all();
	}
	
}
