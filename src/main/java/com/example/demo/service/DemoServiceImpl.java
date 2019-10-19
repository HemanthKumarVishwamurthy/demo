package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	private UserRepository userRepository;

	/*
	 * private static List<User> users;
	 * 
	 * static { User user1 = new User(); user1.setId(1L); user1.setName("Hemanth");
	 * user1.setAddress("Bengaluru");
	 * 
	 * User user2 = new User(); user2.setId(2L); user2.setName("Kumar");
	 * user2.setAddress("Bengaluru");
	 * 
	 * User user3 = new User(); user3.setId(3L); user3.setName("Testing");
	 * user3.setAddress("N/A");
	 * 
	 * users = new ArrayList<User>(); users.add(user1); users.add(user2);
	 * users.add(user3);
	 * 
	 * }
	 */

	@Override
	public List<User> getUsers() {
		// return users;
		List<User> response = new ArrayList<User>();
		List<UserEntity> users = (List<UserEntity>) userRepository.findAll();

		for (UserEntity userEntity : users) {
			User user = new User();
			user.setId(userEntity.getId());
			user.setName(userEntity.getName());
			user.setAddress(userEntity.getAddress());

			response.add(user);
		}

		return response;
	}

	@Override
	public User getUser(Long userId) {
		/*
		 * User requestedUser = null; for (User user : users) { if(user.getId() ==
		 * userId) { requestedUser = user; } } return requestedUser;
		 */
		Optional<UserEntity> userEntity = userRepository.findById(userId);

		User user = new User();
		BeanUtils.copyProperties(userEntity.get(), user);

		return user;
	}

	@Override
	public User getUserByName(String name) {
		User requestedUser = null;
		for (User user : users) {
			if (user.getName().equals(name)) {
				requestedUser = user;
			}
		}
		return requestedUser;
	}

	@Override
	public List<User> saveUser(User user) {
		// call repository to save the data in to database
		// user.setId(users.size()+1);
		// users.add(user);// adding new user into the existing user list

		UserEntity entity = new UserEntity();
		entity.setName(user.getName());
		entity.setAddress(user.getAddress());
		userRepository.save(entity);
		// calling getUsers() created locally
		// to get the list of users
		return getUsers();
	}

	@Override
	public User updateUser(User update) {
		/*
		 * for (User user : users) { if(user.getId() == update.getId()) {
		 * user.setName(update.getName()); user.setAddress(update.getAddress()); } }
		 * 
		 * return users;
		 */
		Optional<UserEntity> entity = userRepository.findById(update.getId());
				
		entity.get().setAddress(update.getAddress());
		entity.get().setName(update.getName());
		
		// save the updated entity
		userRepository.save(entity.get());
		
		return getUser(update.getId());
	}

	@Override
	public List<User> deleteUser(Long userId) {
		/*
		 * User userToRemove = null; for (User user : users) { if (user.getId() ==
		 * userId) { userToRemove = user; } }
		 * 
		 * users.remove(userToRemove); return users;
		 */
		userRepository.deleteById(userId);
		return getUsers();
	}

}
