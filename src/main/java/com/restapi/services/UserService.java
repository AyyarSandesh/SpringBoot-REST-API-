package com.restapi.services;

import java.util.List;

import com.restapi.entities.User;

public interface UserService {
	User createUser(User user);
	User getUserById(Long id);
	List<User> getAllUsers();
	User updateUser(User user);
	void deleteUser(Long id);
}
