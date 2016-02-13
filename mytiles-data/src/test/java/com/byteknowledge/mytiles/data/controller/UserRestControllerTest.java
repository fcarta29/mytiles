package com.byteknowledge.mytiles.data.controller;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;

import java.util.UUID;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.byteknowledge.mytiles.model.User;
import com.byteknowledge.mytiles.service.Service;

public class UserRestControllerTest extends AbstractRestControllerTest {

	@Test
	public void getUserFrank() {
		given()
			.when()
				.get("/users/{id}", Service.USER1_ID)
					.then()
					.statusCode(HttpStatus.SC_OK)
					.contentType("application/json")
					.body("id", Matchers.is(userFrank.getId().toString()))
					.body("userName", Matchers.is(userFrank.getUserName()))
					.body("firstName", Matchers.is(userFrank.getFirstName()))
					.body("lastName", Matchers.is(userFrank.getLastName()))
					.log().all();
	}
	
	@Test
	public void getUsers() {
		given()
			.when()
				.get("/users")
					.then()
					.statusCode(HttpStatus.SC_OK)
					.contentType("application/json")
					.body("size()", Matchers.equalTo(2))
					/*.body("id[0]", Matchers.is(userAndrew.getId().toString()))
					.body("userName[0]", Matchers.is(userAndrew.getUserName()))
					.body("firstName[0]", Matchers.is(userAndrew.getFirstName()))
					.body("lastName[0]", Matchers.is(userAndrew.getLastName()))
					.body("id[1]", Matchers.is(userFrank.getId().toString()))
					.body("userName[1]", Matchers.is(userFrank.getUserName()))
					.body("firstName[1]", Matchers.is(userFrank.getFirstName()))
					.body("lastName[1]", Matchers.is(userFrank.getLastName()))*/
					.log().all();		
	}
	
	@Test
	public void putUser() {

		final User putUser = new User();
		putUser.setId(UUID.fromString("9103f50b-5699-4c2e-b54a-989371567263"));
		putUser.setUserName("pUser");
		putUser.setFirstName("Put");
		putUser.setLastName("User");		
		
		given()
			.body(putUser)
			.contentType("application/json")
			.when()
				.put("/users")
					.then()
					.statusCode(HttpStatus.SC_OK)
					.log().all();
		
		given()
			.when()
				.get("/users")
					.then()
					.statusCode(HttpStatus.SC_OK)
					.contentType("application/json")
					.body("size()", Matchers.equalTo(3))
					/*.body("id[0]", Matchers.is(userAndrew.getId().toString()))
					.body("userName[0]", Matchers.is(userAndrew.getUserName()))
					.body("firstName[0]", Matchers.is(userAndrew.getFirstName()))
					.body("lastName[0]", Matchers.is(userAndrew.getLastName()))
					.body("id[1]", Matchers.is(putUser.getId().toString()))
					.body("userName[1]", Matchers.is(putUser.getUserName()))
					.body("firstName[1]", Matchers.is(putUser.getFirstName()))
					.body("lastName[1]", Matchers.is(putUser.getLastName()))
					.body("id[2]", Matchers.is(userFrank.getId().toString()))
					.body("userName[2]", Matchers.is(userFrank.getUserName()))
					.body("firstName[2]", Matchers.is(userFrank.getFirstName()))
					.body("lastName[2]", Matchers.is(userFrank.getLastName()))*/
					.log().all();
	}
	
	@Test
	public void postUser() {
		
		final String newLastName = "Carta the awesome"; 
		userFrank.setLastName(newLastName);
		
		given()
			.body(userFrank)
			.contentType("application/json")
			.when()
				.post("/users/{id}", Service.USER1_ID)
					.then()
					.statusCode(HttpStatus.SC_OK)
					.log().all();
		
		given()
			.when()
				.get("/users")
					.then()
					.statusCode(HttpStatus.SC_OK)
					.contentType("application/json")
					.body("size()", Matchers.equalTo(2))
					/*.body("id[0]", Matchers.is(userAndrew.getId().toString()))
					.body("userName[0]", Matchers.is(userAndrew.getUserName()))
					.body("firstName[0]", Matchers.is(userAndrew.getFirstName()))
					.body("lastName[0]", Matchers.is(userAndrew.getLastName()))
					.body("id[1]", Matchers.is(userFrank.getId().toString()))
					.body("userName[1]", Matchers.is(userFrank.getUserName()))
					.body("firstName[1]", Matchers.is(userFrank.getFirstName()))
					.body("lastName[1]", Matchers.is(newLastName))*/
					.log().all();
	}	
	
	@Test
	public void deleteUser() {
		
		given()
			.when()
				.delete("/users/{id}", Service.USER2_ID)
					.then()
					.statusCode(HttpStatus.SC_OK)
					.log().all();
		
		given()
			.when()
				.get("/users")
					.then()
					.statusCode(HttpStatus.SC_OK)
					.contentType("application/json")
					.body("size()", Matchers.equalTo(1))
					.body("id[0]", Matchers.is(userFrank.getId().toString()))
					.body("userName[0]", Matchers.is(userFrank.getUserName()))
					.body("firstName[0]", Matchers.is(userFrank.getFirstName()))
					.body("lastName[0]", Matchers.is(userFrank.getLastName()))
					.log().all();
	}	
	
}
