package com.byteknowledge.mytiles.data.controller;

import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.byteknowledge.mytiles.dao.UserDao;
import com.byteknowledge.mytiles.model.User;

@RestController
@RequestMapping("/users")
public class UserRestController extends AbstractRestController {
	
	@Autowired
	private UserDao userDao;
	
    @RequestMapping(method = RequestMethod.GET)
    public Collection<User> getUsers() {
    	return userDao.list();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") final UUID id) {
    	return userDao.get(id);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public void createUser(@RequestBody final User user) {
    	userDao.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void saveUser(@PathVariable("id") final UUID id, final @RequestBody User user) {
    	final User persistedUser = userDao.get(id);
    	persistedUser.setFirstName(user.getFirstName());
    	persistedUser.setLastName(user.getLastName());
    	persistedUser.setUserName(user.getUserName());
    	
    	userDao.save(persistedUser);
    }
    
}
