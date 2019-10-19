package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.DemoService;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

	@Autowired
	private DemoService demoService;
	
	// URL : http://localhost:8080/api/demo/users
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces ="application/json")
	public List<User> getUsers() {
		List<User> user = demoService.getUsers();
		return user;
	}
	
	// URL : http://localhost:8080/api/demo/user/1
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, produces ="application/json")
	public User getUser(@PathVariable Long userId) {
		User user = demoService.getUser(userId);
		return user;
	}
	
	// URL : http://localhost:8080/api/demo/user?userId=1
	// URL : http://localhost:8080/api/demo/user?name=Hemanth
	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
	public User getUserByName(@RequestParam String name) {
		User user = demoService.getUserByName(name);
		return user;
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public List<User> saveUser(@RequestBody User user) {
		List<User> updatedusers = demoService.saveUser(user);
		return updatedusers;
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public User updateUser(@RequestBody User user) {
		User updatedusers = demoService.updateUser(user);
		return updatedusers;
	}
	
	// URL : http://localhost:8080/api/demo/user/1
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE, produces ="application/json")
	public List<User> deleteUser(@PathVariable Long userId) {
		List<User> user = demoService.deleteUser(userId);
		return user;
	}
	
}
