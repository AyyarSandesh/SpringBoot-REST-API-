package com.restapi.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.dto.UserDto;
import com.restapi.entities.User;
import com.restapi.mapper.UserMapper;
import com.restapi.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;
	@Override
	public UserDto createUser(UserDto userDto) {
		//convert UserDto into user Jpa Entity
//		User user=new User(
//					userDto.getId(),
//					userDto.getFirstName(),
//					userDto.getLastName(),
//					userDto.getEmail()
//				);
		User user=UserMapper.mapToUser(userDto);
		
		User savedUser=userRepo.save(user);
		//convert user Jpa  entity to UserDto
//		UserDto savedUserDto=new UserDto(
//					savedUser.getId(),
//					savedUser.getFirstName(),
//					savedUser.getLastName(),
//					savedUser.getEmail()
//				);
		UserDto savedUserDto=UserMapper.mapToUserDto(savedUser);
		
		return savedUserDto;
	}
	@Override
	public UserDto getUserById(Long id) {
		Optional<User> optionalusr=userRepo.findById(id);
		User user=optionalusr.get();
		return UserMapper.mapToUserDto(user);
	}
	@Override
	public List<UserDto> getAllUsers() {
		List<User> Users=userRepo.findAll();
		return Users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
	}
	@Override
	public UserDto updateUser(UserDto userDto) {
		User existingUser=userRepo.findById(userDto.getId()).get();
		existingUser.setFirstName(userDto.getFirstName());
		existingUser.setLastName(userDto.getLastName());
		existingUser.setEmail(userDto.getEmail());
		User updatedUser=userRepo.save(existingUser);
		return UserMapper.mapToUserDto(updatedUser);
	}
	@Override
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}
}