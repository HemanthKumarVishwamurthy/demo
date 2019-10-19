package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface DemoService {

	public List<User> getUsers();

	public User getUser(Long userId);

	public User getUserByName(String name);

	public List<User> saveUser(User user);

	public User updateUser(User user);

	public List<User> deleteUser(Long userId);

}
