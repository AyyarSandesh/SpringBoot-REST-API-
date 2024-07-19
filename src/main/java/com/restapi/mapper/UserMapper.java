package com.restapi.mapper;

import com.restapi.dto.UserDto;
import com.restapi.entities.User;

public class UserMapper {
	//convert user JPA entity to UserDto
	public static UserDto mapToUserDto(User user) {
		UserDto userDto=new UserDto(
					user.getId(),
					user.getFirstName(),
					user.getLastName(),
					user.getEmail()
				);
		return userDto;
	}
	//convert UserDto into User jpa Entity
	public static User mapToUser(UserDto userDto) {
		User user=new User(
					userDto.getId(),
					userDto.getFirstName(),
					userDto.getLastName(),
					userDto.getEmail()
				);
		return user;
	}
}
