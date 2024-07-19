package com.restapi.services;

import java.util.List;

import com.restapi.dto.UserDto;
import com.restapi.entities.User;

public interface UserService {
	UserDto createUser(UserDto userDto);
	UserDto getUserById(Long id);
	List<UserDto> getAllUsers();
	UserDto updateUser(UserDto userDto);
	void deleteUser(Long id);
}
