package com.byteknowledge.mytiles.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.byteknowledge.mytiles.dao.Dao;
import com.byteknowledge.mytiles.dao.UserDao;
import com.byteknowledge.mytiles.model.User;

@RestController
@RequestMapping("/users")
public class UserRestController extends AbstractRestController<User> {
	
	@Autowired
	private UserDao userDao;

	@Override
	protected Dao<User> getDao() {
		return userDao;
	}

	@Override
	protected void merge(final User persistedUser, final User user) {
    	persistedUser.setFirstName(user.getFirstName());
    	persistedUser.setLastName(user.getLastName());
    	persistedUser.setUserName(user.getUserName());
	}
    
}
